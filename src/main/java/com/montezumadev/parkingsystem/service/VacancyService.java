package com.montezumadev.parkingsystem.service;

import com.montezumadev.parkingsystem.dto.VacancyDTO;
import com.montezumadev.parkingsystem.entity.Vacancy;
import com.montezumadev.parkingsystem.repository.VacancyRepository;
import com.montezumadev.parkingsystem.utils.VacancySummary;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {

    private VacancyRepository vacancyRepository;
    private List<Vacancy> vacancies;
    private long totalVacancies;
    private long totalOccupied;
    private long totalAvailable;
    private long totalInactive;



    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public VacancySummary findAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAllOrderedById();
        long totalVacancies = vacancies.size();
        long totalOccupied = vacancies.stream().filter(Vacancy::isOccupied).count();
        long totalAvailable = vacancies.stream().filter(Vacancy::isAvailable).count();
        long totalInactive = vacancies.stream().filter(Vacancy::isInactive).count();

        return new VacancySummary(vacancies, totalVacancies, totalOccupied, totalAvailable, totalInactive);
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


    public VacancyDTO getSPotById(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElse(null);
        if (vacancy != null) {
            VacancyDTO vacancyDTO = new VacancyDTO();
            vacancyDTO.setId(vacancy.getId());
            vacancyDTO.setStatus(vacancy.getStatus());
            vacancyDTO.setOccupiedAt(vacancy.getOccupiedAt());
            return vacancyDTO;
        }
        return null;
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


    public Page<Vacancy> findVacanciesByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return vacancyRepository.findByStatus(status, pageable);
    }
}
