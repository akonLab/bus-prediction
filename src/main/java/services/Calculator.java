package services;

import models.Coordinates;

public class Calculator {
    public Double getDistanceBetween(Coordinates first, Coordinates second) {
        return Math.sqrt(Math.pow(first.getLatitude() - second.getLatitude(), 2) + Math.pow(first.getLongitude() - second.getLongitude(), 2));
    }
}
