package com.ebl.TalentBoard.controller;

import com.ebl.TalentBoard.dto.UserCreateByAdminDTO;
import com.ebl.TalentBoard.dto.UserRegisterDTO;
import com.ebl.TalentBoard.dto.UserResponseDTO;
import com.ebl.TalentBoard.model.User;
import com.ebl.TalentBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO dto) {
        return ResponseEntity.ok(userService.registerCandidate(dto));
    }

    @PostMapping("/admin/create")
    public ResponseEntity<UserResponseDTO> createByAdmin(
            @RequestBody UserCreateByAdminDTO dto,
            @AuthenticationPrincipal User admin) {
        return ResponseEntity.ok(userService.createUserByAdmin(dto, admin));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllUsersPaginated(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteusers(id);
        return ResponseEntity.ok(Map.of("message", "eliminada exitosamente"));
    }
    @GetMapping("/health")
    public String health() {
        return "TalentBoard API is working";
    }
}
