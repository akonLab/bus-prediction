package base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AIBusAPIConn {
    private static final String host = "http://localhost:8080/buses";
    private Boolean isRunning = false;
    private JsonObject allBusDataJson;

    public AIBusAPIConn() {
        getAllBusData();
    }

    public void getConnection() {

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(host).openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                isRunning = false;
            }
            System.out.println(conn.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getAllBusData() {
        JsonObject buses = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(host).openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                isRunning = false;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;

            StringBuilder response = new StringBuilder();
            while ((output = in.readLine()) != null) {
                response.append(output);
            }

            in.close();

            JsonParser parser = new JsonParser();
            buses = (JsonObject) parser.parse(response.toString());

        } catch (IOException e) {
            System.out.println(e.getCause().toString());
        }
        allBusDataJson = buses;
    }


    public ArrayList<JsonObject> getBusDataArrayList() {
        ArrayList<JsonObject> list = new ArrayList<>();

        if (allBusDataJson != null) {
            JsonArray array = allBusDataJson.getAsJsonArray("allBusData");

            for (JsonElement jsonObject : array) {
                try {
                    list.add(jsonObject.getAsJsonObject());
                } catch (NumberFormatException exception) {
                    exception.getCause();
                }
            }
        } else {
            System.out.println("nope, json is empty");
        }
        return list;
    }

    //getter
    public JsonObject getAllBusDataJson() {
        return allBusDataJson;
    }

    public Boolean getRunning() {
        return isRunning;
    }
}
