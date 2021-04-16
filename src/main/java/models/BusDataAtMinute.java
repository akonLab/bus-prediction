package models;

public class BusDataAtMinute {
    private final String time;
    private final Double Longitude;
    private final Double Latitude;
    private final Double distance;

    public BusDataAtMinute(String time, Double longitude, Double latitude, Double distance) {
        this.time = time;
        Longitude = longitude;
        Latitude = latitude;
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getDistance() {
        return distance;
    }
}
