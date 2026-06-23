package com.ebl.TalentBoard.service;

import com.ebl.TalentBoard.dto.InterviewDTO;
import com.ebl.TalentBoard.model.Interview;
import com.ebl.TalentBoard.model.applications;
import com.ebl.TalentBoard.model.User;
import com.ebl.TalentBoard.repository.InterviewRepository;
import com.ebl.TalentBoard.repository.ApplicationRepository;
import com.ebl.TalentBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewService {

    @Autowired private InterviewRepository interviewRepository;
    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private UserRepository userRepository;

    public Interview scheduleInterview(InterviewDTO dto) {
        Interview interview = new Interview();

        // Seteo campo por campo con validación de relaciones
        applications app = applicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
        User interviewer = userRepository.findById(dto.getInterviewerId())
                .orElseThrow(() -> new RuntimeException("Entrevistador no encontrado"));

        interview.setApplicationId(app);
        interview.setResponsible_user(interviewer);
        interview.setHour(dto.getHour());
        interview.setType_interview(dto.getType_interview());
        interview.setResult(dto.getResult());
        interview.setDescription(dto.getDescription());

        if (interview.getHour().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("La fecha de entrevista no puede ser anterior a la actual.");
        }
        return interviewRepository.save(interview);
    }

    public Interview updateInterview(Long id, InterviewDTO dto) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrevista no encontrada"));

        // Seteo campo por campo para la actualización
        applications app = applicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
        User interviewer = userRepository.findById(dto.getInterviewerId())
                .orElseThrow(() -> new RuntimeException("Entrevistador no encontrado"));

        interview.setApplicationId(app);
        interview.setResponsible_user(interviewer);
        interview.setHour(dto.getHour());
        interview.setType_interview(dto.getType_interview());
        interview.setResult(dto.getResult());
        interview.setDescription(dto.getDescription());

        return interviewRepository.save(interview);
    }

    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }

    public List<Interview> getInterviewsByInterviewer(Long interviewerId) {
        return interviewRepository.findAll().stream()
                .filter(i -> i.getResponsible_user().getId().equals(interviewerId))
                .toList();
    }

    public List<Interview> getInterviewsByApplication(Long applicationId) {
        return interviewRepository.findAll().stream()
                .filter(i -> i.getApplicationId().getId().equals(applicationId))
                .toList();
    }
}