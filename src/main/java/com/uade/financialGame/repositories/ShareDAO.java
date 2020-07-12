package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareDAO extends JpaRepository<Share, Long> {
	List<Share> findByPlayer(Player player);
}
