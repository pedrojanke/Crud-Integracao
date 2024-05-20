package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.ParticipantDTO;
import com.example.integracao.entities.Participant;
import com.example.integracao.repository.ParticipantRepository;

import jakarta.transaction.Transactional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Transactional
    public Participant createParticipant(ParticipantDTO dto) {
        try {
            Participant newParticipant = participantRepository.save(new Participant(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newParticipant;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Participant> findParticipantByName(String name) {
        try {
            Optional<Participant> participant = participantRepository.findByName(name);
            return participant;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Participant> listParticipants() {
        try {
            List<Participant> participants = participantRepository.findAll();
            return participants;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Participant findParticipant(String id) {
        try {
            Optional<Participant> participant = participantRepository.findById(id);
            if (participant.isEmpty()) {
                return null;
            }
            return participant.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteParticipant(String id) {
        try {
            participantRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Participant updateParticipantById(String id, ParticipantDTO dto) {
        try {
            Optional<Participant> participant = participantRepository.findById(id);
            if (participant.isEmpty()) {
                return null;
            }
            Participant newParticipant = participantRepository.save(new Participant(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newParticipant;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
