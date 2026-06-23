package com.ebl.TalentBoard.repository;

import com.ebl.TalentBoard.model.applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<applications, Long> {
    // Regla de negocio: Un candidato no puede postularse dos veces a la misma vacante
    boolean existsByCandidateIdAndVacancyId(Long candidateId, Long vacancyId);
}
