package services;

import models.BusStop;
import models.Coordinates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BusStopService {
    private final File file = new File("/Users/adaada/IdeaProjects/bus_java/src/com/company/bus-stop");
    private final ArrayList<BusStop> busStops = new ArrayList<>();

    //constructor
    public BusStopService() {
        generateBusStopList();
    }

    //getter
    public ArrayList<BusStop> getBusStops() {
        return busStops;
    }

    //my method
    public void generateBusStopList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(reader.readLine().replace(",", ".").split(";")));
            for (int i = 0; i < arrayList.size(); i += 3) {
                try {
                    busStops.add(
                            new BusStop(
                                    arrayList.get(i).trim(),
                                    new Coordinates(
                                            Double.parseDouble(arrayList.get(i + 1).trim()),
                                            Double.parseDouble(arrayList.get(i + 2).trim())
                                    )));
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.getCause();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
