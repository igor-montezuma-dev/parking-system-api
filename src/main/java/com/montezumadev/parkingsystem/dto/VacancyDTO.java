package com.montezumadev.parkingsystem.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VacancyDTO {
    private Long id;
    private boolean isPCD;
    private boolean isElderly;
    private boolean isOccupied;
    private boolean isAvailable;
    private boolean isInactive;
    private Timestamp occupiedAt;
    private Timestamp unoccupiedAt;
    private String status;
}
