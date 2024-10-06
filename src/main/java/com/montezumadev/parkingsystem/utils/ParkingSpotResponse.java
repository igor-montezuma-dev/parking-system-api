package com.montezumadev.parkingsystem.utils;

import com.montezumadev.parkingsystem.entity.Vacancy;

import java.util.List;


public class ParkingSpotResponse {
    private List<Vacancy> parkingSpots;

    public ParkingSpotResponse(List<Vacancy> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public List<Vacancy> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<Vacancy> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}