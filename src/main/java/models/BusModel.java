package models;

public class BusModel {
    private Coordinates coordinates;
    private String TSCode;

    //constructor
    public BusModel() {

    }

    public BusModel(Coordinates coordinates, String TSCode) {
        this.coordinates = coordinates;
        this.TSCode = TSCode;
    }

    //getter
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getTSCode() {
        return TSCode;
    }
}
