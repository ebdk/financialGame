package com.uade.financialGame.services;

public interface PlayerService {

    Object payDebt(Long playerId, Integer amount);

    Object donateToCharity(Long playerId);

    Object newMonth(Long playerId);

    Object showPlayerOwnerships(Long playerId);

    Object sellProperty(Long playerId);

    Object rentProperty(Long playerId);

    Object sellShare(Long playerId);
}
