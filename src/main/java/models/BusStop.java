package models;


public class BusStop {
    private String name;
    private Coordinates coordinates;


    public BusStop(String substring) {
    }

    public BusStop(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
