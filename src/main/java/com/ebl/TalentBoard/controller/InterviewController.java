package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.dto.*;
import com.ebl.TalentBoard.model.*;
import com.ebl.TalentBoard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/interviews")
class InterviewController {
    @Autowired private InterviewService service;

    @PostMapping("/resgister") public ResponseEntity<Interview> create(@RequestBody InterviewDTO dto) { return ResponseEntity.ok(service.scheduleInterview(dto)); }
    @PutMapping("/{id}") public ResponseEntity<Interview> update(@PathVariable Long id, @RequestBody InterviewDTO dto) { return ResponseEntity.ok(service.updateInterview(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.deleteInterview(id); return ResponseEntity.noContent().build(); }
}
