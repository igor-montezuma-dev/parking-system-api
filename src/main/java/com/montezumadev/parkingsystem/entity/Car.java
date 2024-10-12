package com.montezumadev.parkingsystem.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "car_plate", nullable = false, length = 8)
    private String carPlate;

    @Basic
    @Column(name = "car_color", nullable = false, length = 16)
    private String carColor;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

}
