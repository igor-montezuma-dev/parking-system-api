package com.montezumadev.parkingsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class VacancyDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String status;
    private Timestamp occupiedAt;
    private Timestamp unoccupiedAt;
}
