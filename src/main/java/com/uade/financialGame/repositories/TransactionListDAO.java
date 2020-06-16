package com.uade.financialGame.repositories;

import com.uade.financialGame.models.TransactionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionListDAO extends JpaRepository<TransactionList, Long> {
}
