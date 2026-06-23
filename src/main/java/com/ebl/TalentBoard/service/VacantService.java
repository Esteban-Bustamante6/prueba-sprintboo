package com.ebl.TalentBoard.service;

import com.ebl.TalentBoard.dto.VacancyDTO;
import com.ebl.TalentBoard.model.*;
import com.ebl.TalentBoard.model.Vacant;
import com.ebl.TalentBoard.repository.UserRepository;
import com.ebl.TalentBoard.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class VacantService {
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private UserRepository userRepository;

    public Vacant createVacancy(VacancyDTO dto) {
        System.out.println("JSON recibido en DTO: " + dto);
        if (dto.getResponsibleId() == null) {
            throw new RuntimeException("El ID del responsable no puede ser nulo");
        }
        User responsible = userRepository.findById(dto.getResponsibleId())
                .orElseThrow(() -> new RuntimeException("Usuario responsable no encontrado"));

        Vacant vacancy = new Vacant();

        vacancy.setTitle(dto.getTitle());
        vacancy.setDescription(dto.getDescription());
        vacancy.setCategory(dto.getCategory());
        vacancy.setModality(dto.getModality());
        vacancy.setSalaryRange(dto.getSalaryRange());
        vacancy.setPublicationDate(LocalDateTime.now());
        vacancy.setStatus(Status.OPEN);
        vacancy.setResponsible(responsible);




        return vacancyRepository.save(vacancy);
    }

    public Vacant updateVacancy(Long id, VacancyDTO dto) {
        Vacant vacancy = vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacante no encontrada"));

            vacancy.setTitle(dto.getTitle());
            vacancy.setDescription(dto.getDescription());
            vacancy.setCategory(dto.getCategory());
            vacancy.setModality(dto.getModality());
            vacancy.setSalaryRange(dto.getSalaryRange());
            vacancy.setResponsible(userRepository.findById(dto.getResponsibleId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
            return vacancyRepository.save(vacancy);
        }

    public List<Vacant> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacant> getVacanciesByCategory(String category) {
            return vacancyRepository.findByCategory(category);
        }

    public List<Vacant> getVacanciesByResponsible(Long responsibleId) {
        return vacancyRepository.findByResponsibleId_Id(responsibleId);
    }



    public void deleteVacancy(Long id) {
        vacancyRepository.deleteById(id);
    }
}

