package com.uade.financialGame.repositories;

import com.uade.financialGame.models.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserDAO extends JpaRepository<GameUser, Long> {
}
