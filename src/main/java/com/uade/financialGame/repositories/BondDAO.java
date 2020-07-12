package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Bond;
import com.uade.financialGame.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondDAO extends JpaRepository<Bond, Long> {
	List<Bond> findByPlayer(Player player);
}
