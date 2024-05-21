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

import com.example.integracao.dto.MediaDTO;
import com.example.integracao.entities.Media;
import com.example.integracao.service.MediaService;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createMedia(@RequestBody MediaDTO dto) {
        Optional<Media> media = mediaService.findMediaByName(dto.name());
        if (media.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Mídia já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Media>("Created", mediaService.createMedia(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Media>>> listMedia() {
        List<Media> listMedia = mediaService.listMedia();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Media>>("Ok", listMedia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Media>> findMedia(@PathVariable String id) {
        Media media = mediaService.findMedia(id);
        if (media == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Media>("Ok", media));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMedia(@PathVariable String id) {
        Media media = mediaService.findMedia(id);
        if (media == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        mediaService.deleteMedia(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMedia(@PathVariable String id, @RequestBody MediaDTO dto) {
        Media media = mediaService.findMedia(id);
        if (media == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Mídia não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Media>("Updated", mediaService.updateMediaById(id, dto)));
    }
}
