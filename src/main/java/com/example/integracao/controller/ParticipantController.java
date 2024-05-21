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

import com.example.integracao.dto.ParticipantDTO;
import com.example.integracao.entities.Participant;
import com.example.integracao.service.ParticipantService;

@RestController
@RequestMapping("/api/v1/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createParticipant(@RequestBody ParticipantDTO dto) {
        Optional<Participant> participant = participantService.findParticipantByName(dto.name());
        if (participant.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Participante já cadastrado."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Participant>("Created", participantService.createParticipant(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Participant>>> listParticipants() {
        List<Participant> listParticipants = participantService.listParticipants();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Participant>>("Ok", listParticipants));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Participant>> findParticipant(@PathVariable String id) {
        Participant participant = participantService.findParticipant(id);
        if (participant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Participant>("Ok", participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteParticipant(@PathVariable String id) {
        Participant participant = participantService.findParticipant(id);
        if (participant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        participantService.deleteParticipant(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateParticipant(@PathVariable String id, @RequestBody ParticipantDTO dto) {
        Participant participant = participantService.findParticipant(id);
        if (participant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Participante não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Participant>("Updated", participantService.updateParticipantById(id, dto)));
    }
}
