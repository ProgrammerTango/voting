package com.tehcsage.pvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tehcsage.pvote.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}

