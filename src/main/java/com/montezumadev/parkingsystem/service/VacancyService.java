package com.montezumadev.parkingsystem.service;

import com.montezumadev.parkingsystem.dto.VacancyDTO;
import com.montezumadev.parkingsystem.entity.Vacancy;
import com.montezumadev.parkingsystem.repository.VacancyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {

    private VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> findAllVacancies() {
        return vacancyRepository.findAll();
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


    public Vacancy updateVacancy(VacancyDTO vacancy) {
        Vacancy updatedVacancy = vacancyRepository.findById(vacancy.getId()).orElse(null);
        if (updatedVacancy != null) {
            updatedVacancy.setStatus(vacancy.getStatus());

            return vacancyRepository.save(updatedVacancy);
        }
        return null;
    }
}
