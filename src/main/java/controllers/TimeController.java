package controllers;

import models.AIBusDataAtMinuteModel;
 import models.BusModel;
import models.AIBusDataModel;
import org.springframework.stereotype.Controller;
import services.BusService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class TimeController {
    private BusService busService = new BusService();
    private List<AIBusDataModel> allBusData = null;
    private List<AIBusDataAtMinuteModel> busDataAtMinuteLis = null;

    public TimeController() {
    }

    public List<AIBusDataModel> getAllBusData() {
        if (allBusData == null) {
            allBusData = new ArrayList<>();
        }

//        ArrayList<BusModel> busModels = busService.getBusModels();
//        for (BusModel busModel : busModels) {
//            allBusData.add(new SomeMinutesPeriodBusData(
//                    busModel.getTSCode(),
//                    getTimePeriodList(busModel)
//            ));
//        }
allBusData.addAll(busService.getMyBusModels());
        allBusData.add(
                new AIBusDataModel(
                        busService.getBusModels().get(0).getTSCode(),
                        getTimePeriodList(busService.getBusModels().get(0))
                ));

        return allBusData;
    }

    public List<AIBusDataAtMinuteModel> getTimePeriodList(BusModel busModel) {

        if (busDataAtMinuteLis == null) {
            busDataAtMinuteLis = new ArrayList<>();
        }
        try {
            if (busDataAtMinuteLis.size() >= 10) {
                busDataAtMinuteLis.remove(0);
                System.out.println("10");
            }
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
//        busDataAtMinuteLis.add(
//                new AIBusDataAtMinuteModel(
//                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
//                        busModel.getDate(),
//                        busModel.getCoordinates().getLongitude(),
//                        busModel.getCoordinates().getLatitude()
//                ));

        return busDataAtMinuteLis;
    }
//    public void start(){
//                TaskMaker taskMaker = new TaskMaker(busModel);
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(taskMaker, 0, 5, TimeUnit.SECONDS);
//    }
//
//    class TaskMaker extends TimeController implements Runnable {
//        private BusModel busModel;
//
//        public TaskMaker(BusModel busModel) {
//            this.busModel = busModel;
//        }
//
//        @Override
//        public void run() {
//            try {
//                if (busDataAtMinuteLis.size() >= 10) {
//                    busDataAtMinuteLis.remove(0);
//                    System.out.println("10");
//                }
//            } catch (NullPointerException e) {
//                System.out.println(e.toString());
//            }
//            busDataAtMinuteLis.add(
//                    new BusDataAtMinute(
//                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
//                            busModel.getDate(),
//                            busModel.getCoordinates().getLongitude(),
//                            busModel.getCoordinates().getLatitude()
//                            ));
//        }
//    }
//

}
