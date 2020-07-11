package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.responses.TurnResponse;
import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.CardDAO;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.PlayerDAO;
import com.uade.financialGame.repositories.TurnDAO;
import com.uade.financialGame.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionType.EXPENSES;
import static java.util.Arrays.asList;

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
    private com.uade.financialGame.repositories.TransactionListDAO transactionListRepository;

    @Autowired
    private com.uade.financialGame.repositories.TransactionDAO transactionRepository;

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


        List<Transaction> thisTurnTransactions = new java.util.ArrayList<>();
        //thisTurnTransactions.addAll(card.getTransactionList().cloneList());

        Turn turn = new Turn(player, card, turnNumber);
        //turn.calculateBalance();


        /*
        switch (card.getEffectType()) {
            case PROPERTY_BUY:
                List<Property> cardProperties = card.getProperties();
                player.addProperties(cardProperties);

                cardProperties.forEach(property -> {
                    Transaction buyTransaction = new Transaction("Compra de " + property.getPropertyName().toString(), EXPENSES, NUMBER, CURRENT, property.getBuyValue());
                    thisTurnTransactions.add(buyTransaction);
                });







                propertyRepository.saveAll(cardProperties);

                break;
            case SHARE_BUY:
                List<Share> cardShares = card.getShares();
                player.addShares(cardShares);

                cardShares.forEach(share -> {
                    Transaction buyTransaction = new Transaction(String.format("Compra de %s Acciones de la Empresa %s", share.getQuantity(), share.getCompany().getName()), EXPENSES, NUMBER, CURRENT, share.getCompany().getShareValue());
                    thisTurnTransactions.add(buyTransaction);
                });

                propertyRepository.saveAll(cardProperties);

                break;
            case BOND_BUY:
                List<Property> cardProperties = card.getProperties();
                player.addProperties(cardProperties);
                propertyRepository.saveAll(cardProperties);

                break;
            case COMPANY_VALUE_CHANGE:
                dsadsad;
                break;
            default:
            case TRANSACTION_ONLY:
                dasds;
                break;
        }






        turnRepository.save(turn);
        transactionRepository.save(expensesTransaction);
        transactionListRepository.saveAll(asList(turnTransactionList, balance));

         */

        return turn.toDto();
    }



    @Override
    public List<TurnResponse> getPlayerTurns(String playerId) {
        Player player = playerRepository.getOne(Long.valueOf(playerId));
        List<TurnResponse> turns = player.getTurns()
                .stream()
                .map(Turn::toDto)
                .collect(Collectors.toList());

        return turns;
    }

    @Override
    public List<TurnResponse> getTurns(String gameId) {
        Game game = gameRepository.getOne(Long.valueOf(gameId));
        List<TurnResponse> turns = game.getPlayers()
                .stream()
                .flatMap(c -> c.getTurns().stream()) //transforms each user's turns into a singe list
                .map(Turn::toDto)
                .collect(Collectors.toList());

        return turns;
    }
}
