package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.dto.*;
import com.ebl.TalentBoard.model.*;
import com.ebl.TalentBoard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// ... (Vacancies y Interviews controllers permanecen igual) ...

// 2. Application Controller (Actualizado para seguridad por rol)
@RestController
@RequestMapping("/api/applications")
class ApplicationController {
    @Autowired private ApplicationService service;

    @PostMapping("/register") public ResponseEntity<applications> create(@RequestBody ApplicationDTO dto) {
        return ResponseEntity.ok(service.createApplication(dto));
    }

    @GetMapping
    public ResponseEntity<List<applications>> getApplications(@AuthenticationPrincipal User currentUser) {
        // 1. Si currentUser es null, es un usuario no autenticado (o anonimo)
        // Puedes decidir devolver lista vacía o todas las aplicaciones.
        if (currentUser == null) {
            return ResponseEntity.ok(service.getAllApplications());
        }

        // 2. Si es candidato, filtramos. Si es otro rol (Admin/Recruiter), vemos todo.
        if (currentUser.getRole() == Role.CANDIDATE) {
            return ResponseEntity.ok(service.getApplicationsByCandidate(currentUser.getId()));
        }

        return ResponseEntity.ok(service.getAllApplications());
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }


}