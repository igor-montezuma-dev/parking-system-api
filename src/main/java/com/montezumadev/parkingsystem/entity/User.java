package com.montezumadev.parkingsystem.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_name", nullable = false, length = 45)
    private String name;

    @Basic
    @Column(name = "document", nullable = false, length = 11)
    private String document;

    @Basic
    @Column(name = "car_plate", nullable = false, length = 8)
    private String carPlate;

    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Basic
    @Column(name = "car_color", nullable = false, length = 16)
    private String carColor;

    @Basic
    @Column(name = "loyalty_card", nullable = false)
    private boolean loyaltyCard;

}
