package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.CompanyRequest;

import java.util.List;

public interface GameService {

    Object createGame(String gameType, String gameDifficulty, String idUser);

    Object fillWithBots(Long gameId);

    Object startGame(Long gameId);

    Object showGameCompanies(Long gameId);

    Object postCompanies(List<CompanyRequest> companyRequestList);
}
