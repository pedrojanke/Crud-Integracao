package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.Classification;

public interface ClassificationRepository extends JpaRepository<Classification , Long>{
    @Query("SELECT cc FROM Classification cc WHERE cc.name = ?1")
    Optional<Classification> findByName(String name);
}
