package com.montezumadev.parkingsystem.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "vacancy")
@Data

public class Vacancy {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "is_pcd", nullable = false)
    private boolean isPCD;

    @Basic
    @Column(name = "is_elderly", nullable = false)
    private boolean isElderly;

    @Basic
    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    @Basic
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Basic
    @Column(name = "is_inactive", nullable = false)
    private boolean isInactive;

    @Basic
    @Column(name = "occupied_at", nullable = true)
    private Timestamp occupiedAt;

    @Basic
    @Column(name = "unoccupied_at", nullable = true)
    private Timestamp unoccupiedAt;

}
