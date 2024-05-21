package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.MediaType;

public interface MediaTypeRepository extends JpaRepository<MediaType , String>{
    @Query("SELECT mt FROM mediatype mt WHERE mt.name = ?1")
    Optional<MediaType> findByName(String name);
    Optional<MediaType> findById(String id);
}