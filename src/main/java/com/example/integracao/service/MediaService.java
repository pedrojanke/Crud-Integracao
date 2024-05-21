package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.MediaDTO;
import com.example.integracao.entities.Media;
import com.example.integracao.repository.MediaRepository;

import jakarta.transaction.Transactional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Transactional
    public Media createMedia(MediaDTO dto) {
        try {
            Media newMedia = mediaRepository.save(new Media(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newMedia;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Media> findMediaByName(String name) {
        try {
            Optional<Media> media = mediaRepository.findByName(name);
            return media;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Media> listMedia() {
        try {
            List<Media> media = mediaRepository.findAll();
            return media;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Media findMedia(String id) {
        try {
            Optional<Media> media = mediaRepository.findById(id);
            if (media.isEmpty()) {
                return null;
            }
            return media.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteMedia(String id) {
        try {
            mediaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Media updateMediaById(String id, MediaDTO dto) {
        try {
            Optional<Media> media = mediaRepository.findById(id);
            if (media.isEmpty()) {
                return null;
            }
            Media newMedia = mediaRepository.save(new Media(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newMedia;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
