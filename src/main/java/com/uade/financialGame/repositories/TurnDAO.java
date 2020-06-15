package com.uade.financialGame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnDAO extends JpaRepository<com.uade.financialGame.models.Turn, Long> {
}
