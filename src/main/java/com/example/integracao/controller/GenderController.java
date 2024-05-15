package com.example.integracao.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integracao.dto.GenderDTO;
import com.example.integracao.entities.Gender;
import com.example.integracao.service.GenderService;

@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createGender(@RequestBody GenderDTO dto) {
        Optional<Gender> gender = genderService.findGenderByName(dto.name());
        if (gender.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Gênero já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Gender>("Created", genderService.createGender(dto)));
    }

    

}
