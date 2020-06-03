package com.uade.financialGame.services.impl;

import com.uade.financialGame.models.Card;
import com.uade.financialGame.repositories.CardDAO;
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

    @Override
    public Object getRandomCard(String cardType, String cardDifficulty) {
        List<Card> allCards = cardRepository.findAll();
        List<Card> filteredCards  = allCards
                .stream()
                .filter(x -> (cardType.equals(x.getCardType().toString())) && (cardDifficulty.equals(x.getCardDifficulty().toString())) )
                .collect(Collectors.toList());
        Random rand = new Random();
        if(!(filteredCards.isEmpty())){
            return filteredCards.get(rand.nextInt(filteredCards.size()));
        } else {
            List<Card> sameTypeCards  = allCards
                    .stream()
                    .filter(x -> cardType.equals(x.getCardType().toString()))
                    .collect(Collectors.toList());
            return sameTypeCards.get(rand.nextInt(sameTypeCards.size()));
        }
    }
}
