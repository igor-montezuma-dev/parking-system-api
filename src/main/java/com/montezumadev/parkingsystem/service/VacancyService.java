package com.montezumadev.parkingsystem.service;

import com.montezumadev.parkingsystem.dto.VacancyDTO;
import com.montezumadev.parkingsystem.entity.Vacancy;
import com.montezumadev.parkingsystem.repository.VacancyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {

    private VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> findAllVacancies() {
        return vacancyRepository.findAllOrderedById();
    }

    @PostConstruct
    public void populateDatabase() {
        try {
            if (vacancyRepository.count() > 0) {
                return;
            }

            int totalVacancies = 40;
            int exclusiveVacancies = (int) (totalVacancies * 0.08);
            List<Vacancy> vacancies = new ArrayList<>();

            for (int i = 0; i < totalVacancies; i++) {
                Vacancy vacancy = new Vacancy();
                vacancy.setAvailable(true);
                vacancy.setOccupied(false);
                vacancy.setInactive(false);
                vacancy.setOccupiedAt(null);
                vacancy.setStatus("available");

                if (i < exclusiveVacancies) {
                    vacancy.setType("elderly");
                } else if (i < 2 * exclusiveVacancies) {
                    vacancy.setType("pcd");
                } else {
                    vacancy.setType("normal");
                }

                vacancies.add(vacancy);
            }

            vacancyRepository.saveAll(vacancies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Vacancy updateVacancy(VacancyDTO vacancyDTO) {
        Vacancy updatedVacancy = vacancyRepository.findById(vacancyDTO.getId()).orElse(null);
        if (updatedVacancy != null) {
            updatedVacancy.setStatus(vacancyDTO.getStatus());
            if (vacancyDTO.getStatus().equals("occupied")) {
                updatedVacancy.setOccupied(true);
                updatedVacancy.setAvailable(false);
                if (vacancyDTO.getOccupiedAt() != null) {
                    updatedVacancy.setOccupiedAt(Timestamp.from(vacancyDTO.getOccupiedAt().toInstant()));
                } else {
                    updatedVacancy.setOccupiedAt(null);
                }
            } else if (vacancyDTO.getStatus().equals("available")) {
                updatedVacancy.setOccupied(false);
                updatedVacancy.setAvailable(true);
                updatedVacancy.setOccupiedAt(null);
            } else if (vacancyDTO.getStatus().equals("inactive")) {
                updatedVacancy.setOccupied(false);
                updatedVacancy.setAvailable(false);
                updatedVacancy.setOccupiedAt(null);
            }
            vacancyRepository.save(updatedVacancy);
        }
        return updatedVacancy;
    }
}
