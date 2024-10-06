package com.montezumadev.parkingsystem.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VacancyDTO {
    private Long id;
    private String status;
    private Timestamp occupiedAt;
    private Timestamp unoccupiedAt;
}
