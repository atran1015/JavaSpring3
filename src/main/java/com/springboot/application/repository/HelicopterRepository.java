package com.springboot.application.repository;

import com.springboot.application.model.Helicopter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelicopterRepository extends JpaRepository<Helicopter, Long> {
}