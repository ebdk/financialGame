package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthDAO extends JpaRepository<Month, Long> {
}
