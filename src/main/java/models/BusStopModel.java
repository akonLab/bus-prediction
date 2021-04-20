package models;


public class BusStopModel {
    private String name;
    private CoordinatesModel coordinates;


    public BusStopModel(String substring) {
    }

    public BusStopModel(String name, CoordinatesModel coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public CoordinatesModel getCoordinates() {
        return coordinates;
    }

}
