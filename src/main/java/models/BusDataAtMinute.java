package models;

public class BusDataAtMinute {
    private final String time;
    private final Double Longitude;
    private final Double Latitude;

    public BusDataAtMinute(String time, Double longitude, Double latitude ) {
        this.time = time;
        Longitude = longitude;
        Latitude = latitude;
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
}
