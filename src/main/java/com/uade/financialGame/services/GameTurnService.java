package com.uade.financialGame.services;

import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.customRequests.GetGameTurnsRequest;
import com.uade.financialGame.messages.customRequests.GetGameUserTurnsRequest;

public interface GameTurnService {

    Object createGameTurn(CreateGameTurnRequest createGameTurnRequest);

    Object getGameUserTurns(GetGameUserTurnsRequest getGameUserTurnsRequest);

    Object getGameTurns(GetGameTurnsRequest getGameUserTurnsRequest);

}
