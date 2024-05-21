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

import com.example.integracao.dto.MediaTypeDTO;
import com.example.integracao.entities.MediaType;
import com.example.integracao.service.MediaTypeService;

@RestController
@RequestMapping("/api/v1/mediatype")
public class MediaTypeController {

    @Autowired
    private MediaTypeService mediaTypeService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createMediaType(@RequestBody MediaTypeDTO dto) {
        Optional<MediaType> mediaType = mediaTypeService.findMediaTypeByName(dto.name());
        if (mediaType.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Tipo de mídia já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<MediaType>("Created", mediaTypeService.createMediaType(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MediaType>>> listMediaTypes() {
        List<MediaType> listMediaTypes = mediaTypeService.listMediaTypes();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<MediaType>>("Ok", listMediaTypes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MediaType>> findMediaType(@PathVariable String id) {
        MediaType mediaType = mediaTypeService.findMediaType(id);
        if (mediaType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<MediaType>("Ok", mediaType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMediaType(@PathVariable String id) {
        MediaType mediaType = mediaTypeService.findMediaType(id);
        if (mediaType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        mediaTypeService.deleteMediaType(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMediaType(@PathVariable String id, @RequestBody MediaTypeDTO dto) {
        MediaType mediaType = mediaTypeService.findMediaType(id);
        if (mediaType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Tipo de mídia não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<MediaType>("Updated", mediaTypeService.updateMediaTypeById(id, dto)));
    }
}
