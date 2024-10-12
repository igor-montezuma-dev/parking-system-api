// src/main/java/com/montezumadev/parkingsystem/utils/ParkingSpotResponse.java
package com.montezumadev.parkingsystem.utils;

import com.montezumadev.parkingsystem.entity.Vacancy;
import lombok.Data;

import java.util.List;

@Data
public class ParkingSpotResponse {
    private List<Vacancy> parkingSpots;
    private long totalVacancies;
    private long totalOccupied;
    private long totalAvailable;
    private long totalInactive;

    public ParkingSpotResponse(List<Vacancy> parkingSpots, long totalVacancies, long totalOccupied, long totalAvailable, long totalInactive) {
        this.parkingSpots = parkingSpots;
        this.totalVacancies = totalVacancies;
        this.totalOccupied = totalOccupied;
        this.totalAvailable = totalAvailable;
        this.totalInactive = totalInactive;
    }


}