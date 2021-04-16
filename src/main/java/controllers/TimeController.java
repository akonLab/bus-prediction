package controllers;


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
    private BusService busService;
    private BusStopService busStopService;
    private List<SomeMinutesPeriodBusData> allBusData = null;
    private Queue<Double> tenMinDistanceList = null;
    private Calculator calculator = new Calculator();

    public TimeController() {
        busService = new BusService();
        busStopService = new BusStopService();
    }


    public List<SomeMinutesPeriodBusData> getAllBusData() {
        if (allBusData == null) {
            allBusData = new ArrayList<>();
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(calculator, 0, 1, TimeUnit.MINUTES);
        }

//        for (BusModel busModel : busService.getBusModels()) {
//            for (BusStop busStop : busStopService.getBusStops()) {
//                allBusData.add(new SomeMinutesPeriodBusData(
//                        busModel.getTSCode(),
//                        generate10MinList(busStop, busModel)
//                ));
//            }
//        }

        BusStop busStop = busStopService.getBusStops().get(0);
        for (BusModel busModel : busService.getBusModels()) {
            allBusData.add(new SomeMinutesPeriodBusData(
                    busModel.getTSCode(),
                    calculator.generate10MinList(busStop, busModel)
            ));

        }
        return allBusData;
    }


    public Double generateBusDataForBusPredictionAPI() {
        BusModel busModel = busService.getBusModels().get(0);
        BusStop busStop = busStopService.getBusStops().get(0);
//        System.out.println("bus(" + busModel.getTSCode() + ") and busStop(" + busStop.getName() + ") = " + busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates()));

        return busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates());
    }

    public Queue<Double> getTenMinDistanceList(BusStop busStop, BusModel busModel) {
        if (tenMinDistanceList.isEmpty()) {
            tenMinDistanceList = new LinkedList<>();
        }
        if (tenMinDistanceList.size() >= 10) {
            tenMinDistanceList.remove();
        }
        tenMinDistanceList.add(busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates()));

        Collections.reverse((List<?>) tenMinDistanceList);
        return tenMinDistanceList;
    }

    //    public HashMap<String, Double> generateBusDataForBusPredictionAPI() {
//
//        HashMap<String, Double> generatedDistance = new HashMap<>();
//        for (BusModel busModel : busService.getBusModels()) {
//            for (BusStop busStop : busStopService.getBusStops()) {
//                generatedDistance.put(busModel.getTSCode(), busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates()));
////                System.out.println("bus("+busModel.getTSCode()+") and busStop("+busStop.getName()+") = "+busService.getDistanceBetween(busModel.getCoordinates(),busStop.getCoordinates()));
//            }
//        }
//        return generatedDistance;
//    }

    class Calculator implements Runnable {
        private List<BusDataAtMinute> busDataAtMinuteLis=null;

        public Calculator() {
        }


        @Override
        public void run() {

        }

        public List<BusDataAtMinute> generate10MinList(BusStop busStop, BusModel busModel) {
            LocalDateTime today = LocalDateTime.now();
            if (busDataAtMinuteLis==null) {
                busDataAtMinuteLis = new ArrayList<>();
            }
            try {
                if (busDataAtMinuteLis.size() >= 10) {
                    busDataAtMinuteLis.remove(busDataAtMinuteLis.size() - 1);
                }
            }catch (NullPointerException e){
                System.out.println(   e.toString());;
            }


            busDataAtMinuteLis.add(new BusDataAtMinute(
                    today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                    busModel.getCoordinates().getLongitude(),
                    busModel.getCoordinates().getLatitude(),
                    busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates())
            ));

            return busDataAtMinuteLis;
        }
    }
}
