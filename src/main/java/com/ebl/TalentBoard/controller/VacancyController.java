package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.dto.*;
import com.ebl.TalentBoard.model.*;
import com.ebl.TalentBoard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Vacancy Controller
@RestController
        @RequestMapping("/api/vacancies")
class VacancyController {
    @Autowired private VacantService service;

    @PostMapping("/register") public ResponseEntity<Vacant> create(@RequestBody VacancyDTO dto) { return ResponseEntity.ok(service.createVacancy(dto)); }
    @PutMapping("/{id}") public ResponseEntity<Vacant> update(@PathVariable Long id, @RequestBody VacancyDTO dto) { return ResponseEntity.ok(service.updateVacancy(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.deleteVacancy(id); return ResponseEntity.noContent().build(); }
    @GetMapping("/category/{cat}") public ResponseEntity<List<Vacant>> getByCategory(@PathVariable String cat) { return ResponseEntity.ok(service.getVacanciesByCategory(cat)); }
}
