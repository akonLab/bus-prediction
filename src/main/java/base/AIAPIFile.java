package base;

import com.google.gson.*;
import models.AIBusDataAtMinuteModel;
import models.AIBusDataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AIAPIFile {
    private static final File file = new File("C:\\Users\\praktikant\\IdeaProjects\\bus-prediction\\src\\main\\java\\base\\AIBusDataFile");
    private ArrayList<JsonObject> AIBusDataModels = null;
    private JsonObject jsonObject;
    private final HashMap<String, AIBusDataModel> AIBusModelHashMap = new HashMap<>();

    //constructor
    public AIAPIFile() {
        if (generateAIBusDataModelListFromFile()) {
            structureTimeList();
            convertJsonDataIntoAIBusModelHashMap();
        }
    }

    //getter
    public HashMap<String, AIBusDataModel> getAIBusModelHashMap() {
        return AIBusModelHashMap;
    }

    //rewrite file method
    public void rewriteAIFile(List<AIBusDataModel> aiBusDataModels) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write("{\"allBusData\": " + new Gson().toJson(aiBusDataModels) + "}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //get text from file methods
    public boolean generateAIBusDataModelListFromFile() {
        try {
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

            JsonParser parser = new JsonParser();
            jsonObject = (JsonObject) parser.parse(content);

        } catch (NullPointerException | IOException | ClassCastException e) {
            System.out.println("ai file ex");
            return false;
        }
        System.out.println("noai ex");
        return true;
    }

    public void structureTimeList() {
        ArrayList<JsonObject> list = new ArrayList<>();

        if (jsonObject != null) {
            JsonArray array = jsonObject.getAsJsonArray("allBusData");

            for (JsonElement jsonObject : array) {
                try {
                    list.add(jsonObject.getAsJsonObject());
                } catch (NumberFormatException | IllegalStateException exception) {
                    exception.getCause();
                }
            }
        } else {
            System.out.println("nope, json is empty");
        }
        System.out.println("88 AI Api " + list.size());
        AIBusDataModels = list;
    }

    public void convertJsonDataIntoAIBusModelHashMap() {
        System.out.println(AIBusDataModels);
        try {
            for (JsonObject object : Objects.requireNonNull(AIBusDataModels)) {//O(n)
                AIBusModelHashMap.put(
                        object.get("TSCode").getAsString(),
                        new AIBusDataModel(
                                object.get("TSCode").getAsString(),
                                getAIBusDataAtMinuteModelList(object, object.get("TSCode").getAsString())));
            }
        } catch (NullPointerException e) {
            System.out.println("94 null");
        }
    }

    public List<AIBusDataAtMinuteModel> getAIBusDataAtMinuteModelList(JsonObject object, String TSCode) {
        ArrayList<AIBusDataAtMinuteModel> busDataAtMinutes = new ArrayList<>();
        try {
            for (JsonElement jsonObject : object.getAsJsonArray("allBusData")) {//loop for jsonArray("allBusData)

                if (jsonObject.getAsJsonObject().get("tsCode").getAsString().equals(TSCode)) {
                    for (JsonElement arr : jsonObject.getAsJsonObject().get("busDataAtMinutes").getAsJsonArray()) {// loop for jsonArray("busDataAtMinutes)
                        busDataAtMinutes.add(
                                new AIBusDataAtMinuteModel(
                                        arr.getAsJsonObject().get("RecordedTime").getAsString(),
                                        arr.getAsJsonObject().get("Longitude").getAsDouble(),
                                        arr.getAsJsonObject().get("Latitude").getAsDouble()
                                ));
                    }
                }
            }
        } catch (NumberFormatException | NullPointerException exception) {
            exception.getCause();
        }


        return busDataAtMinutes;
    }

}
