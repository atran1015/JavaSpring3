package com.springboot.application.repository;

import com.springboot.application.model.Jet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JetRepository extends JpaRepository<Jet, Long> {
}