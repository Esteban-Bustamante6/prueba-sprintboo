package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applications")
public class ApplicationViewController {

    @Autowired
    private ApplicationService applicationService;

    // Listar todas las postulaciones
    @GetMapping("/applications/view")
    public String listApplications(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications/list";
    }
}