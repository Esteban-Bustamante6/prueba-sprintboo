package com.ebl.TalentBoard.dto;

import lombok.Data;

@Data
public class ApplicationDTO {
    private Long candidateId;
    private Long vacancyId;
    private String observations;
}
