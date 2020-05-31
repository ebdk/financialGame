package com.uade.financialGame.repositories;

import com.uade.financialGame.models.GameLobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLobbyDAO extends JpaRepository<GameLobby, Long> {
}
