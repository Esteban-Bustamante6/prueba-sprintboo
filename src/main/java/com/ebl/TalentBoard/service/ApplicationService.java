package com.ebl.TalentBoard.service;

import com.ebl.TalentBoard.dto.ApplicationDTO;
import com.ebl.TalentBoard.model.applications;
import com.ebl.TalentBoard.model.ApplicationStatus;
import com.ebl.TalentBoard.model.User;
import com.ebl.TalentBoard.model.Vacant;
import com.ebl.TalentBoard.repository.ApplicationRepository;
import com.ebl.TalentBoard.repository.UserRepository;
import com.ebl.TalentBoard.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private VacancyRepository vacancyRepository;

    public applications createApplication(ApplicationDTO dto) {
        // 1. Validar existencia de entidades
        User candidate = userRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        Vacant vacancy = vacancyRepository.findById(dto.getVacancyId())
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));

        // 2. Reglas de negocio
        if (vacancy.getStatus().toString().equals("CLOSED")) {
            throw new RuntimeException("No se pueden recibir postulaciones para vacantes cerradas.");
        }
        if (applicationRepository.existsByCandidateIdAndVacancyId(dto.getCandidateId(), dto.getVacancyId())) {
            throw new RuntimeException("El candidato ya se encuentra postulado a esta vacante.");
        }

        // 3. Seteo campo por campo
        applications application = new applications();
        application.setCandidate(candidate);
        application.setVacancy(vacancy);
        application.setApplicationDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.RECEIVED);
        application.setDescription(dto.getObservations());

        return applicationRepository.save(application);
    }

    public applications updateApplication(Long id, ApplicationDTO dto) {
        applications application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        User candidate = userRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        Vacant vacancy = vacancyRepository.findById(dto.getVacancyId())
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));

        application.setCandidate(candidate);
        application.setVacancy(vacancy);
        application.setDescription(dto.getObservations());

        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<applications> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findAll().stream()
                .filter(a -> a.getCandidate().getId().equals(candidateId))
                .toList();
    }
    public List<applications> getAllApplications() {
        return applicationRepository.findAll();
    }

}