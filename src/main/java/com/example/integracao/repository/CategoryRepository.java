package com.example.integracao.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integracao.entities.Category;

public interface CategoryRepository extends JpaRepository<Category , Long>{
    @Query("SELECT c FROM category c WHERE c.name = ?1")
    Optional<Category> findByName(String name);
}