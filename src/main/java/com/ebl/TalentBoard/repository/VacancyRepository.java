package com.ebl.TalentBoard.repository;

import com.ebl.TalentBoard.model.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacant, Long> {
    List<Vacant> findByCategory(String category);

    Optional<Vacant> findById(Long id);
    List<Vacant> findByResponsibleId_Id(Long ResponsibleId);
}
