package com.example.integracao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<ApiResponse<List<Gender>>> listGender() {
        List<Gender> listGender = genderService.listGender();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Gender>>("Ok", listGender));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Gender>> findGender(@PathVariable String id) {
        Gender gender = genderService.findGender(id);
        if (gender == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Gender>("Ok", gender));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteGender(@PathVariable String id) {
        Gender gender = genderService.findGender(id);
        if (gender == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        genderService.deleteGender(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateService(@PathVariable String id, @RequestBody GenderDTO dto) {
        Gender gender = genderService.findGender(id);
        if (gender == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Gênero não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Gender>("Updated", genderService.updateGenderById(id, dto)));
    }

}
