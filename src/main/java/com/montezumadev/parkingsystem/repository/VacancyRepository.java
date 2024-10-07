package com.montezumadev.parkingsystem.repository;

import com.montezumadev.parkingsystem.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {


    @Query("SELECT v FROM Vacancy v ORDER BY v.id ASC")
    List<Vacancy> findAllOrderedById();

}
