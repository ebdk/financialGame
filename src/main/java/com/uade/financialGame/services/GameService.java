package com.uade.financialGame.services;

import com.uade.financialGame.messages.customRequests.CreateGameRequest;

public interface GameService {

    Object createGame(CreateGameRequest createGameRequest);

}
