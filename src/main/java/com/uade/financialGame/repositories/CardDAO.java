package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDAO extends JpaRepository<Card, Long> {
}
