package com.springboot.application.repository;

import com.springboot.application.model.Glider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // To specify that the underlying interface is a repository.  A repo is created by extending JpaRepository
public interface GliderRepository extends JpaRepository<Glider, Long> { // JPA specific extension for Repo, contains all the APPIs for basic CRUD operations, pagination, and sorting
}