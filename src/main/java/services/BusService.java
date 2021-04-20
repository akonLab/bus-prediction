package services;

import base.AIAPIFile;
import base.APIConn;
import com.google.gson.JsonObject;
import models.AIBusDataAtMinuteModel;
import models.AIBusDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusService {
    //old
    private final APIConn conn = new APIConn();
    private ArrayList<AIBusDataModel> busModels;
    private HashMap<String, AIBusDataAtMinuteModel> minArr = null;
    private final HashMap<String, AIBusDataModel> busModelHashMap = new HashMap<>();
    //from file
    AIAPIFile aiapiFile = new AIAPIFile();

    public BusService() {
        convertJsonDataIntoBusModelHashMap();
        System.out.println("nusmodel hashmap size " + busModelHashMap.size());
    }


    //old
    public ArrayList<AIBusDataModel> getBusModelsArrayList() {
        busModels = new ArrayList<>(busModelHashMap.values());
        return busModels;
    }

    public HashMap<String, AIBusDataModel> getBusModelHashMap() {
        return busModelHashMap;
    }

    public HashMap<String, AIBusDataAtMinuteModel> getMinArr() {
        return minArr;
    }

    public void convertJsonDataIntoBusModelHashMap() {
        minArr = new HashMap<>();
        System.out.println( conn.getBusesByLineCode(15).get(0).get("TSCode").getAsString());
        minArr.put(
                conn.getBusesByLineCode(15).get(0).get("TSCode").getAsString(),
                new AIBusDataAtMinuteModel(
                        conn.getBusesByLineCode(15).get(0).get("RecordedTime").getAsString(),
                        conn.getBusesByLineCode(15).get(0).get("Longitude").getAsDouble(),
                        conn.getBusesByLineCode(15).get(0).get("Latitude").getAsDouble()
                ));

//        for (JsonObject object : conn.getBusesByLineCode(15)) {//O(n)
//            minArr.put(
//                    object.get("TSCode").getAsString(),
//                    new AIBusDataAtMinuteModel(
//                            object.get("RecordedTime").getAsString(),
//                            object.get("Longitude").getAsDouble(),
//                            object.get("Latitude").getAsDouble()
//                    ));
//
//        }
        System.out.println("51" + minArr.get(0).toString());
        System.out.println("converted hashmap");
    }

    List<AIBusDataAtMinuteModel> getList(JsonObject object, String code) {
        return aiapiFile.getAIBusDataAtMinuteModelList(object, code);
    }

    public AIBusDataModel getByTsCode(String TSCode) {
        if (busModelHashMap.containsKey(TSCode)) {
            return busModelHashMap.get(TSCode);
        }
        return null;
    }


    //from file
    public HashMap<String, AIBusDataModel> getAIBusModelHashMapFromAIFile() {
        return aiapiFile.getAIBusModelHashMap();
    }

    public void saveAIBusModelToAIFile(List<AIBusDataModel> list) {
        aiapiFile.rewriteAIFile(list);
    }
}