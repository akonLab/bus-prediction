package models;


public class CoordinatesModel {
    private Double Longitude;
    private Double Latitude;

    public CoordinatesModel() {
    }

    public CoordinatesModel(Double latitude, Double longitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }
}
