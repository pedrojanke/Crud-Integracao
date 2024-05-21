package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.MediaTypeDTO;
import com.example.integracao.entities.MediaType;
import com.example.integracao.repository.MediaTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class MediaTypeService {

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Transactional
    public MediaType createMediaType(MediaTypeDTO dto) {
        try {
            MediaType newMediaType = mediaTypeRepository.save(new MediaType(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newMediaType;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<MediaType> findMediaTypeByName(String name) {
        try {
            Optional<MediaType> mediaType = mediaTypeRepository.findByName(name);
            return mediaType;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<MediaType> listMediaTypes() {
        try {
            List<MediaType> mediaTypes = mediaTypeRepository.findAll();
            return mediaTypes;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public MediaType findMediaType(String id) {
        try {
            Optional<MediaType> mediaType = mediaTypeRepository.findById(id);
            if (mediaType.isEmpty()) {
                return null;
            }
            return mediaType.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteMediaType(String id) {
        try {
            mediaTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public MediaType updateMediaTypeById(String id, MediaTypeDTO dto) {
        try {
            Optional<MediaType> mediaType = mediaTypeRepository.findById(id);
            if (mediaType.isEmpty()) {
                return null;
            }
            MediaType newMediaType = mediaTypeRepository.save(new MediaType(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newMediaType;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
