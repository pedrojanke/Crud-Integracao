package com.example.integracao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.integracao.dto.GenderDTO;
import com.example.integracao.entities.Gender;
import com.example.integracao.repository.GenderRepository;

import jakarta.transaction.Transactional;

@Service
public class GenderService {


    @Autowired
    private GenderRepository genderRepository;

    @Transactional
    public Gender createGender(GenderDTO dto){
        try{
            Gender newGender = genderRepository.save(new Gender(null, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newGender;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public  Optional<Gender> findGenderByName(String name){
        try{
            Optional<Gender> gender = genderRepository.findByName(name);
            return gender;

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Gender> listGender(){
        try{
            List<Gender> gender = genderRepository.findAll();
            return gender;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Gender findGender(String id){
        try{
            Optional<Gender> gender = genderRepository.findById(id);
            if(gender.isEmpty()) {
                return null;
            }

            return gender.get();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteGender(String id){
        try{
            genderRepository.deleteById(id);
         } catch (Exception e){
             throw new RuntimeException(e.getMessage());
         }
    }

    public Gender updateGenderById(String id, GenderDTO dto){
        try{
            Optional<Gender> gender = genderRepository.findById(id);
            if(gender.isEmpty()) {
                return null;
            }
            Gender newGender = genderRepository.save(new Gender(id, dto.name(), LocalDate.now(), dto.inactivationDate()));
            return newGender;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
