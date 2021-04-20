package models;

public class AIBusDataAtMinuteModel {
    private final String mytime;
    private final String date;
    private final Double Longitude;
    private final Double Latitude;

    public AIBusDataAtMinuteModel(String mytime, String date, Double longitude, Double latitude) {
        this.mytime = mytime;
        this.date = date;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public String getMytime() {
        return mytime;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }
}
