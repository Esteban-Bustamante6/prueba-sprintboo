package com.ebl.TalentBoard.dto;

import com.ebl.TalentBoard.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateByAdminDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role; // El administrador elige si es ADMIN o RECRUITER
}