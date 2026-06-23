package com.ebl.TalentBoard.dto;

import lombok.Data;

@Data
public class VacancyDTO {
    private String title;
    private String description;
    private String category;
    private String modality;
    private Double salaryRange;
    private Long ResponsibleId;
}

