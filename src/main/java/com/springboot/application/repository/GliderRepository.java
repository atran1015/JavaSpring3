package com.springboot.application.repository;

import com.springboot.application.entity.Glider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GliderRepository extends JpaRepository<Glider, String> {
}