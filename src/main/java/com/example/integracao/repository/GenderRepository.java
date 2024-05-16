package com.example.integracao.repository;

import java.util.Optional;
import java.util.UUID; // Import the UUID class

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.integracao.entities.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender , String>{
    @Query("SELECT g FROM gender g WHERE g.name = ?1")
    Optional<Gender> findByName(String name);
    Optional<Gender> findById(String id);
}