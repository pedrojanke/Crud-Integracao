package com.example.integracao.entities;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "midia")
public class Midia {    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", length = 64)
    private UUID id;
    @Column(length = 45, unique = true, nullable = false)
    private String nomemidia;
    @Column(nullable = false)
    private Date datadecadastro;
    @Column(nullable = false)
    private Date datainativo;
}
