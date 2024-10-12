package com.montezumadev.parkingsystem.utils;

import com.montezumadev.parkingsystem.entity.Vacancy;
import lombok.Data;

import java.util.List;

@Data
public class VacancySummary {

    private List<Vacancy> vacancies;
    private long totalVacancies;
    private long totalOccupied;
    private long totalAvailable;
    private long totalInactive;

    public VacancySummary(List<Vacancy> vacancies,
                          long totalVacancies,
                          long totalOccupied,
                          long totalAvailable,
                          long totalInactive) {
        this.vacancies = vacancies;
        this.totalVacancies = totalVacancies;
        this.totalOccupied = totalOccupied;
        this.totalAvailable = totalAvailable;
        this.totalInactive = totalInactive;
    }
}
