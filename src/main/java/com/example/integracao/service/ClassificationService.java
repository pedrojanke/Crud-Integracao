package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.ClassificationDTO;
import com.example.integracao.entities.Classification;
import com.example.integracao.repository.ClassificationRepository;

import jakarta.transaction.Transactional;

@Service
public class ClassificationService {

    @Autowired
    private ClassificationRepository classificationRepository;

    @Transactional
    public Classification createClassification(ClassificationDTO dto) {
        try {
            Classification newClassification = classificationRepository.save(new Classification(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newClassification;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Classification> findClassificationByName(String name) {
        try {
            Optional<Classification> classification = classificationRepository.findByName(name);
            return classification;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Classification> listClassification() {
        try {
            List<Classification> classification = classificationRepository.findAll();
            return classification;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Classification findClassification(String id) {
        try {
            Optional<Classification> classification = classificationRepository.findById(id);
            if (classification.isEmpty()) {
                return null;
            }
            return classification.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteClassification(String id) {
        try {
            classificationRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Classification updateClassificationById(String id, ClassificationDTO dto) {
        try {
            Optional<Classification> classification = classificationRepository.findById(id);
            if (classification.isEmpty()) {
                return null;
            }
            Classification newClassification = classificationRepository.save(new Classification(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newClassification;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
