package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender , Long>{
    @Query("SELECT g FROM gender g WHERE g.name = ?1")
    Optional<Gender> findByName(String name);
}