package com.ebl.TalentBoard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}