package com.uade.financialGame.repositories;

import com.uade.financialGame.models.User;
import com.uade.financialGame.models.User.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    List<User> findByRank(UserRank rank);
}
