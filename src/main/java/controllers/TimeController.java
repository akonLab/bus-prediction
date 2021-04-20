package controllers;

import models.AIBusDataAtMinuteModel;
import models.AIBusDataModel;
import org.springframework.stereotype.Controller;
import services.BusService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
public class TimeController implements Runnable {
    private final BusService busService = new BusService();
    private final List<AIBusDataModel> allBusData = new ArrayList<>();

    private List<AIBusDataAtMinuteModel> busDataAtMinuteLis = new ArrayList<>();
    String tscode = "A266";

    public TimeController() {
    }

    @Override
    public void run() {
        getPrevAIBusData();
        addNewBusData();
        writeData();

        System.out.println();
    }

    void writeData() {
        busService.saveAIBusModelToAIFile(allBusData);
    }

    boolean getPrevAIBusData() {
        if (busService.getAIBusModelHashMapFromAIFile() != null) {
            AIBusDataModel model = (Objects.requireNonNull(busService.getAIBusModelHashMapFromAIFile()).get(tscode));
            allBusData.add(model);
            System.out.println("prev bus data was added " + busService.getAIBusModelHashMapFromAIFile());
            return true;
        }
        return false;
    }

    //adding new data from not AI API
    void addNewBusData() {
        HashMap<String, AIBusDataAtMinuteModel> minArr = busService.getMinArr();
        System.out.println("50"+minArr.get(0).toString());
        System.out.println("add new bus data 2" + allBusData);
        try {
            for (AIBusDataModel model : allBusData) {
                if (model.getBusDataAtMinutes().size() >= 10) {
                    model.getBusDataAtMinutes().remove(0);
                }
                model.getBusDataAtMinutes().add(
                        minArr.get(model.getTsCode())
                );
            }
        } catch (NullPointerException e) {
            e.getCause();
        }

//        allBusData.get()
//        allBusData.get(allBusData.indexOf())
//        allBusData.addAll(busService.getBusModelsArrayList());
        System.out.println("add new bus data 3" + allBusData);
    }

    public List<AIBusDataModel> getAllBusData() {
        return allBusData;
    }

}
