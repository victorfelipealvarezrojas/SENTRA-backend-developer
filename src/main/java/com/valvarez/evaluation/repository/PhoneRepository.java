package com.valvarez.evaluation.repository;

import com.valvarez.evaluation.entity.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {}
