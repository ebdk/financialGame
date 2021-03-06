package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.responses.TurnResponse;
import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.*;
import com.uade.financialGame.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.uade.financialGame.models.Card.EffectType.*;
import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static com.uade.financialGame.utils.MathUtils.getPercentage;
import static java.util.stream.Collectors.toList;

@Service
public class TurnServiceImpl implements TurnService {

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private CardDAO cardRepository;

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private TurnDAO turnRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

    @Autowired
    private TransactionDAO transactionRepository;

    @Autowired
    private PropertyDAO propertyRepository;

    @Autowired
    private CompanyDAO companyRepository;

    @Autowired
    private ShareDAO shareRepository;

    @Autowired
    private BondDAO bondRepository;



    @Override
    public Object createTurn(Long playerId, Long cardId, Long boxId, Integer turnNumber) {
        Player player;
        Optional<Player> playerSearch = playerRepository.findById(playerId);
        if(playerSearch.isPresent()) {
            player = playerSearch.get();
        } else {
            return new MessageResponse("Jugador no existe");
        }

        Card card;
        Optional<Card> cardSearch =  cardRepository.findById(cardId);
        if(cardSearch.isPresent()) {
            card = cardSearch.get();
        } else {
            return new MessageResponse("Carta no existe");
        }

        List<Transaction> thisTurnTransactions = new ArrayList<>();

        Turn turn = new Turn(player, card, turnNumber);

        if(PROPERTY_BUY.equals(card.getEffectType())) {

            List<Property> cardProperties = card.getProperties();
            List<Property> clonedProperties = cloneProperties(cardProperties, player);
            player.addProperties(clonedProperties);

            List<Transaction> transactions = cardProperties
                    .stream()
                    .map(property -> new Transaction("Compra de " + property.getPropertyName(), EXPENSES, NUMBER, CURRENT, property.getBuyValue()))
                    .collect(toList());

            thisTurnTransactions.addAll(transactions);
            propertyRepository.saveAll(clonedProperties);
        }
        if(SHARE_BUY.equals(card.getEffectType())) {

            List<Share> cardShares = card.getShares();
            player.addShares(cardShares);

            List<Transaction> transactions = cardShares
                    .stream()
                    .map(share -> new Transaction(String.format("Compra de %s Acciones de la Empresa %s", share.getQuantity(), share.getCompany().getName()), EXPENSES, NUMBER, CURRENT, share.getCompany().getShareValue()))
                    .collect(toList());
            thisTurnTransactions.addAll(transactions);

            playerRepository.save(player);
            shareRepository.saveAll(cardShares);
        }
        if(BOND_BUY.equals(card.getEffectType())) {

            List<Bond> cardBonds = card.getBonds();
            List<Bond> clonedBonds = cloneBonds(cardBonds);
            player.addBonds(clonedBonds);

            List<Transaction> transactions = cardBonds
                    .stream()
                    .map(bond -> new Transaction("Compra de bonos de " + bond.getSmallDescription(), EXPENSES, NUMBER, CURRENT, bond.getBuyValue()))
                    .collect(toList());
            thisTurnTransactions.addAll(transactions);

            bondRepository.saveAll(clonedBonds);
        }
        if(COMPANY_VALUE_CHANGE.equals(card.getEffectType())) {
            List<CompanyChanges> companyChanges = card.getCompanyChanges();
            List<Company> companies = companyRepository.findByGame(player.getGame());


            companyChanges.forEach(companyChange -> {
                Company companyToBeChanged = companies
                        .stream()
                        .filter(company -> companyChange.getCompany().getName().equals(company.getName()))
                        .collect(toList()).get(0);
                companyToBeChanged.changeCompanyAttribute(companyChange.getAttribute(), companyChange.getValue());
            });

            //companyChanges.forEach(companyChange -> companies.forEach(company -> company.ifIsCompanyApplyChange(companyChange)));
            companyRepository.saveAll(companies);

            return companies.stream().map(Company::toDto).collect(toList());
        }
        if(TRANSACTION_ONLY.equals(card.getEffectType())) {
            if(card.getTargetType().equals(Card.TargetType.PERSONAL)) {
                thisTurnTransactions = card.getTransactionList().cloneList();
            } else if (card.getTargetType().equals(Card.TargetType.GLOBAL)) {
                List<Player> players = playerRepository.findByGame(player.getGame());

                if(!card.getTransactionList().getPassivePercentage().isEmpty()){

                    Transaction percentagePassive = card.getTransactionList().getPassivePercentage().get(0);

                    players.forEach(player1 ->  {
                        Integer playerDebt = player.getBalanceValuesMap().get(PASSIVE);
                        Integer amount = getPercentage(playerDebt, percentagePassive.getValue());
                        Transaction passiveTransaction = new Transaction(String.format("Aumento de valor de Deudas por Inflacion del %s", percentagePassive.getValue()), PASSIVE, NUMBER, CURRENT, amount);
                        player1.addTransactionsToBalance(passiveTransaction);
                    });

                } else {
                    thisTurnTransactions = card.getTransactionList().cloneList();
                    players.forEach(player1 -> {
                        if(!player.getPlayerId().equals(player1.getPlayerId())) {
                            player1.addTransactionsToBalance(card.getTransactionList().cloneList());
                        }
                    });
                }

                playerRepository.saveAll(players);
            }
        }

        player.addTransactionsToBalance(thisTurnTransactions);

        turnRepository.save(turn);
        playerRepository.save(player);


        if(COMPANY_VALUE_CHANGE.equals(card.getEffectType())) {
        }

        return player.toDto();
    }

    private List<Bond> cloneBonds(List<Bond> cardBonds) {
        List<Bond> clonedBonds = new ArrayList<>();
        cardBonds.forEach(cardBond -> {
            clonedBonds.add(new Bond(cardBond));
        });
        return clonedBonds;
    }

    private List<Property> cloneProperties(List<Property> cardProperties, Player player) {
        List<Property> clonedProperties = new ArrayList<>();
        cardProperties.forEach(cardProperty -> {
            clonedProperties.add(new Property(cardProperty, player));
        });
        return clonedProperties;
    }

    @Override
    public List<TurnResponse> getPlayerTurns(String playerId) {
        Player player = playerRepository.getOne(Long.valueOf(playerId));
        List<TurnResponse> turns = player.getTurns()
                .stream()
                .map(Turn::toDto)
                .collect(toList());

        return turns;
    }

    @Override
    public List<TurnResponse> getTurns(String gameId) {
        Game game = gameRepository.getOne(Long.valueOf(gameId));
        List<TurnResponse> turns = game.getPlayers()
                .stream()
                .flatMap(c -> c.getTurns().stream()) //transforms each user's turns into a singe list
                .map(Turn::toDto)
                .collect(toList());

        return turns;
    }
}
