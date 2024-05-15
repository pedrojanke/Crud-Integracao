package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.Media;

public interface MediaRepository extends JpaRepository<Media , Long>{
    @Query("SELECT m FROM media m WHERE m.name = ?1")
    Optional<Media> findByName(String name);
}