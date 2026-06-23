package com.ebl.TalentBoard.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vacant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class Vacant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 55)
    private String title;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "Work_modality", nullable = false, length = 60)
    private String modality;

    @Column(name = "salary" , nullable = false)
    private double salaryRange;

    @Column(name = "publication_date", nullable = false)
    private LocalDateTime publicationDate ;



    @ManyToOne
    @JoinColumn(name = "responsible_user_id", nullable = false)
    private User Responsible;

    @Enumerated(EnumType.STRING)
    private Status status;


}
