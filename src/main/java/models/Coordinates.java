package models;


public class Coordinates {
    private Double Longitude;
    private Double Latitude;

    public Coordinates() {
    }

    public Coordinates(Double latitude, Double longitude) {
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
