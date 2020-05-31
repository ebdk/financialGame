package com.uade.financialGame.repositories;

import com.uade.financialGame.models.GameTurn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTurnDAO extends JpaRepository<GameTurn, Long> {
}
