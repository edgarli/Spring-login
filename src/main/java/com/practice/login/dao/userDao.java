package com.practice.login.dao;

import com.practice.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
