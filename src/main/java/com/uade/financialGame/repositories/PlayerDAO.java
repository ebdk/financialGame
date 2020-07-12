package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
	List<Player> findByGame(Game game);

	//List<Player> findByPlayerId(List<Long> playerIds);
}
