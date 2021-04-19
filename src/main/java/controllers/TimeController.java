package controllers;


import example.Task;
import models.BusDataAtMinute;
import models.BusModel;
import models.BusStop;
import models.SomeMinutesPeriodBusData;
import services.BusService;
import services.BusStopService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeController {
    private BusService busService = new BusService();
    private List<SomeMinutesPeriodBusData> allBusData = null;
    private List<BusDataAtMinute> busDataAtMinuteLis = null;

    public TimeController() {
    }


    public List<SomeMinutesPeriodBusData> getAllBusData() {
        if (allBusData == null) {
            allBusData = new ArrayList<>();
        }

//        ArrayList<BusModel> busModels=busService.getBusModels();
//        for (BusModel busModel : busModels) {
////            if (allBusData.contains(busModel)){
////
////            }
//            allBusData.add(new SomeMinutesPeriodBusData(
//                    busModel.getTSCode(),
//                    getTimePeriodList(  busModel)
//            ));
//
//        }
        busService = new BusService();
        allBusData.add(
                new SomeMinutesPeriodBusData(
                busService.getBusModels().get(0).getTSCode(),
                getTimePeriodList(busService.getBusModels().get(0))
        ));

        return allBusData;
    }

    public List<BusDataAtMinute> getTimePeriodList(BusModel busModel) {
        if (busDataAtMinuteLis == null) {
            busDataAtMinuteLis = new ArrayList<>();
        }
//        busDataAtMinuteLis.add(new BusDataAtMinute(
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
//                busModel.getCoordinates().getLongitude(),
//                busModel.getCoordinates().getLatitude()
//         ));
        TaskMaker taskMaker = new TaskMaker(busModel);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(taskMaker, 0, 5, TimeUnit.SECONDS);

        return busDataAtMinuteLis;
    }

    class TaskMaker extends TimeController implements Runnable {
        private BusModel busModel;

        public TaskMaker(BusModel busModel) {
            this.busModel = busModel;
        }

        @Override
        public void run() {
            try {
                if (busDataAtMinuteLis.size() >= 10) {
                    System.out.println("10");
                    busDataAtMinuteLis.remove(0);
                }
            } catch (NullPointerException e) {
                System.out.println(e.toString());
            }
            busDataAtMinuteLis.add(new BusDataAtMinute(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                    busModel.getCoordinates().getLongitude(),
                    busModel.getCoordinates().getLatitude()
            ));
        }
    }


}
