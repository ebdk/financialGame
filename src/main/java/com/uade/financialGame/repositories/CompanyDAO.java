package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Company;
import com.uade.financialGame.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {
	List<Company> findByGame(Game game);

	//List<Company> findIsStatic(boolean b);

	List<Company> findBySaveType(Company.SaveType saveType);
}
