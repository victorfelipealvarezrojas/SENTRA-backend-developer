package com.valvarez.evaluation.repository;

import com.valvarez.evaluation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByName(String username);

    Boolean existsByEmail(String email);
}