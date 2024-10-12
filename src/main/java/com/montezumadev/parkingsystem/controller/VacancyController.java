package com.montezumadev.parkingsystem.controller;

import com.montezumadev.parkingsystem.dto.VacancyDTO;
import com.montezumadev.parkingsystem.entity.Vacancy;
import com.montezumadev.parkingsystem.service.VacancyService;
import com.montezumadev.parkingsystem.utils.ParkingSpotResponse;
import com.montezumadev.parkingsystem.utils.VacancySummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/all")
    public ResponseEntity<ParkingSpotResponse> getAllVacancies() {
        VacancySummary vacancySummary = vacancyService.findAllVacancies();
        ParkingSpotResponse response = new ParkingSpotResponse(
                vacancySummary.getVacancies(),
                vacancySummary.getTotalVacancies(),
                vacancySummary.getTotalOccupied(),
                vacancySummary.getTotalAvailable(),
                vacancySummary.getTotalInactive()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyDTO> getSpotById(@PathVariable Long id) {
        VacancyDTO parkingSpot = vacancyService.getSPotById(id);
        if (parkingSpot != null) {
            return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VacancyDTO> updateVacancy(@PathVariable Long id, @RequestBody VacancyDTO vacancyDTO) {
        vacancyDTO.setId(id);
        Vacancy updatedVacancy = vacancyService.updateVacancy(vacancyDTO);
        if (updatedVacancy != null) {
            return new ResponseEntity<>(vacancyDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}