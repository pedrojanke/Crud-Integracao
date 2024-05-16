package com.example.integracao.entities;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 64, updatable = false)
    private String id;
    @Column(length = 45, unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private LocalDate registrionDate;
    @Column(nullable = true)
    private Date inactivationDate;
}
