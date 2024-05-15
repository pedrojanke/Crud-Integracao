package com.example.integracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integracao.service.GeneroService;

@RestController
@RequestMapping("/api/v1/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<ApiResponse> createService(@RequestBody GeneroDTO dto) {
        Optional<dto> service = serviceServices.findServiceByName(dto.name());
        if (service.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Serviço já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Services>("Created", serviceServices.createService(dto)));
    }
    
}
