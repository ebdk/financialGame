package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.requests.CardRequest;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.TransactionList;
import com.uade.financialGame.repositories.CardDAO;
import com.uade.financialGame.repositories.TransactionDAO;
import com.uade.financialGame.repositories.TransactionListDAO;
import com.uade.financialGame.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDAO cardRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

    @Autowired
    private TransactionDAO transactionRepository;

    @Override
    public Object getRandomCard(String cardType, String cardDifficulty) {
        List<Card> allCards = cardRepository.findAll();
        List<Card> filteredCards  = allCards
                .stream()
                .filter(x -> (cardType.equals(x.getType().toString())) && (cardDifficulty.equals(x.getDifficulty().toString())) )
                .collect(Collectors.toList());
        Random rand = new Random();
        if(!(filteredCards.isEmpty())){
            return filteredCards.get(rand.nextInt(filteredCards.size())).toDto();
        } else {
            List<Card> sameTypeCards  = allCards
                    .stream()
                    .filter(x -> cardType.equals(x.getType().toString()))
                    .collect(Collectors.toList());
            return sameTypeCards.get(rand.nextInt(sameTypeCards.size())).toDto();
        }
    }


    @Override
    public Object createCard(CardRequest cardRequest) {
        Card newCard = new Card(cardRequest);

        newCard.getTransactionList().getTransactions().forEach(x -> transactionRepository.save(x));
        transactionListRepository.save(newCard.getTransactionList());
        cardRepository.save(newCard);
        return newCard.toDto();
    }

}
