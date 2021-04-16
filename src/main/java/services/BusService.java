package services;

import base.APIConn;
import com.google.gson.JsonObject;
import models.BusModel;
import models.Coordinates;

import java.util.ArrayList;

public class BusService {
    private final APIConn conn = new APIConn();
    private final ArrayList<BusModel> busModels = new ArrayList<>();

    public BusService() {
    }

    public ArrayList<BusModel> getBusModels() {
        for (JsonObject object : conn.sort(conn.showBusData())) {//O(n)
            busModels.add(new BusModel(
                    new Coordinates(
                            object.get("Latitude").getAsDouble(),
                            object.get("Longitude").getAsDouble()),
                    object.get("TSCode").getAsString()));
        }
        return busModels;
    }

    public Double getDistanceBetween(Coordinates first, Coordinates second) {
        return Math.sqrt(Math.pow(first.getLatitude() - second.getLatitude(), 2) + Math.pow(first.getLongitude() - second.getLongitude(), 2));
    }
}
