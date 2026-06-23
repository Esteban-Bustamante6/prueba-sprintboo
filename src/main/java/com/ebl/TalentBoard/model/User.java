package com.ebl.TalentBoard.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email",nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password",nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)

    @Column(nullable = false, length = 50)
    private Role role;




}
