package services;

import base.APIConn;
import base.AIBusAPIConn;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.AIBusDataAtMinuteModel;
import models.BusModel;
import models.CoordinatesModel;
import models.AIBusDataModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BusService {
    //old
    private final APIConn conn = new APIConn();
    private ArrayList<BusModel> busModels;
    private final HashMap<String, BusModel> busModelHashMap = new HashMap<>();
    //new
    private final AIBusAPIConn AIBusAPIConn = new AIBusAPIConn();
    private ArrayList<AIBusDataModel> newBusModels;
    private final HashMap<String, AIBusDataModel> newBusModelHashMap = new HashMap<>();

    public BusService() {
        convertJsonDataIntoBusModelArray();
    }

    //old
    public ArrayList<BusModel> getBusModels() {
        busModels = new ArrayList<>(busModelHashMap.values());
        return busModels;
    }

    public void convertJsonDataIntoBusModelArray() {
        for (JsonObject object : conn.getBusesByLineCode(15)) {//O(n)
            busModelHashMap.put(
                    object.get("TSCode").getAsString(),
                    new BusModel(
                            new CoordinatesModel(
                                    object.get("Latitude").getAsDouble(),
                                    object.get("Longitude").getAsDouble()),
                            object.get("TSCode").getAsString(),
                            object.get("RecordedTime").getAsString())
            );
        }
    }

    public BusModel getByTsCode(String TSCode) {
        if (busModelHashMap.containsKey(TSCode)) {
            return busModelHashMap.get(TSCode);
        }
        return null;
    }

    //new
    public ArrayList<AIBusDataModel> getMyBusModels() {
        newBusModels = new ArrayList<>(newBusModelHashMap.values());
        return newBusModels;
    }

    public void myconvertJsonDataIntoBusModelArray() {
        for (JsonObject object : AIBusAPIConn.getBusDataArrayList()) {//O(n)
            newBusModelHashMap.put(
                    object.get("TSCode").getAsString(),
                    new AIBusDataModel(
                            object.get("TSCode").getAsString(),
                            getBusDataAtMinArr(object, object.get("TSCode").getAsString())));
        }
    }

    public List<AIBusDataAtMinuteModel> getBusDataAtMinArr(JsonObject object, String TSCode) {
        ArrayList<AIBusDataAtMinuteModel> busDataAtMinutes = new ArrayList<>();

        for (JsonElement jsonObject : object.getAsJsonArray()) {// loop for jsonArray("allBusData)
            try {
                if (jsonObject.getAsJsonObject().get("tsCode").getAsString().equals(TSCode)) {
                    for (JsonElement arr : jsonObject.getAsJsonObject().get("busDataAtMinutes").getAsJsonArray()) {// loop for jsonArray("busDataAtMinutes)
                        busDataAtMinutes.add(
                                new AIBusDataAtMinuteModel(
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                                        arr.getAsJsonObject().get("RecordedTime").getAsString(),
                                        arr.getAsJsonObject().get("Longitude").getAsDouble(),
                                        arr.getAsJsonObject().get("Latitude").getAsDouble()
                                ));
                    }
                }
            } catch (NumberFormatException exception) {
                exception.getCause();
            }
        }

        return busDataAtMinutes;
    }

    public HashMap<String, AIBusDataModel> getNewBusModelHashMap() {
        return newBusModelHashMap;
    }
}