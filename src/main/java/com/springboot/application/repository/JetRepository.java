package com.springboot.application.repository;

import com.springboot.application.entity.Jet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JetRepository extends JpaRepository<Jet, String> {
}