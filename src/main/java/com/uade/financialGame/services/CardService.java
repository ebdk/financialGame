package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.CardRequest;

public interface CardService {
    Object getRandomCard(String cardType, String cardDifficulty);

    Object createCard(CardRequest cardRequest);
}
