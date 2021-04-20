package services;

import base.AIAPIFile;
import base.APIConn;
import models.AIBusDataAtMinuteModel;
import models.AIBusDataModel;

import java.util.HashMap;
import java.util.List;

public class BusService {
    //old
    private final APIConn conn = new APIConn();
    private HashMap<String, AIBusDataAtMinuteModel> minArr = null;
    private final HashMap<String, AIBusDataModel> busModelHashMap = new HashMap<>();
    //from file
    AIAPIFile aiapiFile = new AIAPIFile();

    public BusService() {
        convertJsonDataIntoBusModelHashMap();
        System.out.println("nusmodel hashmap size " + busModelHashMap.size());
    }

    //add min element into time arr
    public HashMap<String, AIBusDataAtMinuteModel> getMinArr() {
        return minArr;
    }

    public void convertJsonDataIntoBusModelHashMap() {
        minArr = new HashMap<>();
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
        System.out.println("converted hashmap");
    }
public AIBusDataAtMinuteModel getMinElem(){
        return  new AIBusDataAtMinuteModel(
                conn.getBusesByLineCode(15).get(0).get("RecordedTime").getAsString(),
                conn.getBusesByLineCode(15).get(0).get("Longitude").getAsDouble(),
                conn.getBusesByLineCode(15).get(0).get("Latitude").getAsDouble()
        );
}
    //from file
    public HashMap<String, AIBusDataModel> getAIBusModelHashMapFromAIFile() {
        return aiapiFile.getAIBusModelHashMap();
    }

    public void saveAIBusModelToAIFile(List<AIBusDataModel> list) {
        aiapiFile.rewriteAIFile(list);
    }
}