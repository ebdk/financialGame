package com.uade.financialGame.services;

import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.responses.GameTurnResponse;

import java.util.List;

public interface GameTurnService {

    Object createGameTurn(Long userId, Long cardId, Long boxId, Integer turnNumber);

    List<GameTurnResponse> getGameUserTurns(String gameUserId);

    List<GameTurnResponse> getGameTurns(String gameId);

}
