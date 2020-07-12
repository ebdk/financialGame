package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionDAO extends JpaRepository<Profession, Long> {
	List<Profession> findByDifficulty(Game.GameDifficulty gameDifficulty);
}
