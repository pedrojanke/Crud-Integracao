package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant , String>{
    @Query("SELECT p FROM participant p WHERE p.name = ?1")
    Optional<Participant> findByName(String name);
    Optional<Participant> findById(String id);
}