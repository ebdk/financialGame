package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Bond;
import com.uade.financialGame.models.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondDAO extends JpaRepository<Bond, Long> {
}
