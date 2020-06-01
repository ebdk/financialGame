package com.uade.financialGame.services;

import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.customRequests.GetGameTurnsRequest;
import com.uade.financialGame.messages.customRequests.GetGameUserTurnsRequest;
import com.uade.financialGame.messages.responses.GameTurnResponse;

import java.util.List;

public interface GameTurnService {

    Object createGameTurn(CreateGameTurnRequest createGameTurnRequest);

    List<GameTurnResponse> getGameUserTurns(GetGameUserTurnsRequest getGameUserTurnsRequest);

    List<GameTurnResponse> getGameTurns(GetGameTurnsRequest getGameUserTurnsRequest);

}
