package com.luxoft.dbapp.dao.security;

import com.luxoft.dbapp.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userName);
}
