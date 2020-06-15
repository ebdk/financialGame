package com.uade.financialGame.services;

import com.uade.financialGame.messages.responses.TurnResponse;

import java.util.List;

public interface TurnService {

    Object createTurn(Long userId, Long cardId, Long boxId, Integer turnNumber);

    List<TurnResponse> getPlayerTurns(String playerId);

    List<TurnResponse> getTurns(String gameId);

}
