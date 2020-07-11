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
import static com.uade.financialGame.models.Transaction.TransactionType.EXPENSES;
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

        //TransactionList balance = player.getBalance();

        List<Transaction> thisTurnTransactions = new ArrayList<>();

        Turn turn = new Turn(player, card, turnNumber);

        if(PROPERTY_BUY.equals(card.getEffectType())) {

            List<Property> cardProperties = card.getProperties();
            player.addProperties(cardProperties);

            List<Transaction> transactions = cardProperties
                    .stream()
                    .map(property -> new Transaction("Compra de " + property.getPropertyName().toString(), EXPENSES, NUMBER, CURRENT, property.getBuyValue()))
                    .collect(toList());

            thisTurnTransactions.addAll(transactions);
            propertyRepository.saveAll(cardProperties);
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
            player.addBonds(cardBonds);

            List<Transaction> transactions = cardBonds
                    .stream()
                    .map(bond -> new Transaction(String.format("Compra de %s Bonos de la Empresa %s", bond.getQuantity(), bond.getCompany().getName()), EXPENSES, NUMBER, CURRENT, bond.getCompany().getShareValue()))
                    .collect(toList());
            thisTurnTransactions.addAll(transactions);

            bondRepository.saveAll(cardBonds);
        }
        if(COMPANY_VALUE_CHANGE.equals(card.getEffectType())) {
            List<CompanyChanges> companyChanges = card.getCompanyChanges();
            List<Company> companies = companyRepository.findByGameItBelongs(player.getGame());

            companyChanges.forEach(companyChange -> {
                Company companyToBeChanged = companies
                        .stream()
                        .filter(company -> companyChange.getCompany().getName().equals(company.getName()))
                        .collect(toList()).get(0);
                companyToBeChanged.changeCompanyAttribute(companyChange.getAttribute(), companyChange.getValue());

                companyRepository.save(companyToBeChanged);
            });
        }
        if(TRANSACTION_ONLY.equals(card.getEffectType())) {
            if(card.getTargetType().equals(Card.TargetType.PERSONAL)) {
                thisTurnTransactions = card.getTransactionList().getTransactions();
            } else if (card.getTargetType().equals(Card.TargetType.GLOBAL)) {
                List<Player> players = playerRepository.findByGame(player.getGame());
                players.forEach(player1 -> {
                    if(!player.getPlayerId().equals(player1.getPlayerId())) {
                        player1.addTransactionsToBalance(card.getTransactionList().getTransactions());
                    }
                });
                playerRepository.saveAll(players);
            }
        }

        //balance.addTransactions(thisTurnTransactions);

        player.addTransactionsToBalance(thisTurnTransactions);

        turnRepository.save(turn);
        playerRepository.save(player);
        //transactionRepository.save(expensesTransaction);
        //transactionListRepository.save(balance);

        return turn.toDto();
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
