package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.dto.VacancyDTO;
import com.ebl.TalentBoard.service.ApplicationService;
import com.ebl.TalentBoard.service.VacantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    @Autowired
    private VacantService vacantService;

    @Autowired
    private ApplicationService applicationService;

    // --- NAVEGACIÓN Y VISTAS DE ENTRADA ---

    @GetMapping("/")
    public String home() {
        // Redirige al listado principal al entrar a la raíz
        return "redirect:/vacancies";
    }


    // --- VISTAS DE VACANTES (LISTADO Y FORMULARIOS) ---

    @GetMapping("/vacancies")
    public String listVacancies(Model model) {
        // Carga la lista de vacantes para mostrar en la tabla/layout
        model.addAttribute("vacancies", vacantService.getAllVacancies());
        return "vacancies/list";
    }

    @GetMapping("/vacancies/new")
    public String showVacancyForm(Model model) {
        // Prepara un DTO vacío para el formulario de creación
        model.addAttribute("vacancyDTO", new VacancyDTO());
        return "vacancies/form";
    }

    @PostMapping("/vacancies/save")
    public String saveVacancy(@ModelAttribute("vacancyDTO") VacancyDTO vacancyDTO) {
        // Procesa el guardado desde el formulario HTML
        vacantService.createVacancy(vacancyDTO);
        return "redirect:/vacancies";
    }

    // --- VISTAS DE POSTULACIONES ---

    @GetMapping("/applications")
    public String listApplications(Model model) {
        // Carga el listado de aplicaciones registradas
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications/list";
    }
}