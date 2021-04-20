package services;

import models.CoordinatesModel;

public class CalculatorService {
    public Double getDistanceBetween(CoordinatesModel first, CoordinatesModel second) {
        return Math.sqrt(Math.pow(first.getLatitude() - second.getLatitude(), 2) + Math.pow(first.getLongitude() - second.getLongitude(), 2));
    }
}
