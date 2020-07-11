package com.uade.financialGame.repositories;

import com.uade.financialGame.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDAO extends JpaRepository<Property, Long> {
}
