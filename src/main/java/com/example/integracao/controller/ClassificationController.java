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

import com.example.integracao.dto.ClassificationDTO;
import com.example.integracao.entities.Classification;
import com.example.integracao.service.ClassificationService;

@RestController
@RequestMapping("/api/v1/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createClassification(@RequestBody ClassificationDTO dto) {
        Optional<Classification> classification = classificationService.findClassificationByName(dto.name());
        if (classification.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Classificação já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Classification>("Created", classificationService.createClassification(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Classification>>> listClassification() {
        List<Classification> listClassification = classificationService.listClassification();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Classification>>("Ok", listClassification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Classification>> findClassification(@PathVariable String id) {
        Classification classification = classificationService.findClassification(id);
        if (classification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Classification>("Ok", classification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClassification(@PathVariable String id) {
        Classification classification = classificationService.findClassification(id);
        if (classification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        classificationService.deleteClassification(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateClassification(@PathVariable String id, @RequestBody ClassificationDTO dto) {
        Classification classification = classificationService.findClassification(id);
        if (classification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Classificação não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Classification>("Updated", classificationService.updateClassificationById(id, dto)));
    }
}
