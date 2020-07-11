package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Profession;
import com.uade.financialGame.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDAO extends JpaRepository<Share, Long> {
}
