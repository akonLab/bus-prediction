package models;

public class BusModel {
    private CoordinatesModel coordinates;
    private String TSCode;
    private String date;

    //constructor
    public BusModel() {

    }

    public BusModel(CoordinatesModel coordinates, String TSCode) {
        this.coordinates = coordinates;
        this.TSCode = TSCode;
    }

    public BusModel(CoordinatesModel coordinates, String TSCode, String date) {
        this.coordinates = coordinates;
        this.TSCode = TSCode;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    //getter
    public CoordinatesModel getCoordinates() {
        return coordinates;
    }

    public String getTSCode() {
        return TSCode;
    }
}
