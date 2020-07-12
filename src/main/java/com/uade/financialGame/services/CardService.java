package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.CardRequest;

import java.util.List;

public interface CardService {
    Object getRandomCard(String cardType, String cardDifficulty);

    Object createCard(List<CardRequest> cardRequestList);
}
