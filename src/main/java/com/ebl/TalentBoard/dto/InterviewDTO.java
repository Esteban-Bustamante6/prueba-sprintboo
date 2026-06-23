package com.ebl.TalentBoard.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class InterviewDTO {
    private Long applicationId;
    private LocalDateTime hour;
    private String type_interview;
    private String result;
    private String description;
    private Long interviewerId;
    private Long Responsible_user;
}
