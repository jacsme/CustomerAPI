package com.customer.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
