package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Long> {
}
