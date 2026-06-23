package com.ebl.TalentBoard.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class applications { // Nombre de clase corregido

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vacancyId", nullable = false)
    private Vacant vacancy; // Nombre de atributo limpio

    @OneToOne
    @JoinColumn(name = "candidateId", nullable = false)
    private User candidate; // Nombre de atributo limpio

    @Column(name = "date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}