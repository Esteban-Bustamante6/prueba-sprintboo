package com.ebl.TalentBoard.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "responsible_user_id", nullable = false)
    private User Responsible_user;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "result", nullable = false )
    private String result;

    @Column(name = "type_interview", nullable = false, length = 40)
    private String type_interview;

    @Column(name = "interview_date", nullable = false)
    private LocalDateTime hour;


    @OneToOne
    @JoinColumn(name = "application_id")
    private applications applicationId;


}
