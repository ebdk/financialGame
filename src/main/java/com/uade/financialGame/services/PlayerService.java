package com.uade.financialGame.services;

public interface PlayerService {

    Object payDebt(Long playerId, Integer amount);

    Object donateToCharity(Long playerId);

    Object nextMonth(Long playerId);

    Object showPlayerOwnerships(Long playerId);

    Object sellProperty(Long playerId, Long propertyId);

    Object rentProperty(Long playerId, Long propertyId);

    Object sellShare(Long playerId, Long shareId, Integer quantity);
}
