package com.uade.financialGame.services;

public interface GameService {

    Object createGame(String gameType, String gameDifficulty, String idUser);

    Object fillWithBots(Long gameId);

    Object modifyPlayersProfessions(Long gameId);

    Object showGameCompanies(Long gameId);
}
