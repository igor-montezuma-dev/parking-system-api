package com.montezumadev.parkingsystem.controller;

import com.montezumadev.parkingsystem.dto.VacancyDTO;
import com.montezumadev.parkingsystem.entity.Vacancy;
import com.montezumadev.parkingsystem.service.VacancyService;
import com.montezumadev.parkingsystem.utils.ParkingSpotResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<Vacancy> vacancies = vacancyService.findAllVacancies();
        ParkingSpotResponse response = new ParkingSpotResponse(vacancies);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}