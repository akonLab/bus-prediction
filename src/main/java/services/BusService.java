package services;

import base.APIConn;
import com.google.gson.JsonObject;
import models.BusModel;
import models.Coordinates;

import java.util.ArrayList;

public class BusService {
    private  APIConn conn = new APIConn();
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
    public Coordinates getCurrentCoordinates(String tscode){
         conn.sort(conn.showBusData()).
    }


}
