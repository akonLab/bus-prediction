package controllers;


import models.BusDataAtMinute;
import models.BusModel;
import models.BusStop;
import models.SomeMinutesPeriodBusData;
import services.BusService;
import services.BusStopService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeController implements Runnable {
    private BusService busService;
    private BusStopService busStopService;
    private List<SomeMinutesPeriodBusData> allBusData = null;
    private Queue<Double> tenMinDistanceList = null;
    private List<BusDataAtMinute> busDataAtMinuteLis;

    @Override
    public void run() {
        busService = new BusService();
        busStopService = new BusStopService();
    }

    public List<SomeMinutesPeriodBusData> getAllBusData() {
        if (allBusData == null) {
            allBusData = new ArrayList<>();
        }

        for (BusModel busModel : busService.getBusModels()) {
            for (BusStop busStop : busStopService.getBusStops()) {
                allBusData.add(new SomeMinutesPeriodBusData(
                        busModel.getTSCode(),
                        generate10MinList(busStop, busModel)
                ));
            }
        }
        return allBusData;
    }

    public List<BusDataAtMinute> generate10MinList(BusStop busStop, BusModel busModel) {
        LocalDateTime today = LocalDateTime.now();
        if (busDataAtMinuteLis.isEmpty()) {
            busDataAtMinuteLis = new ArrayList<>();
        }

        busDataAtMinuteLis.add(new BusDataAtMinute(
                today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                busModel.getCoordinates().getLongitude(),
                busModel.getCoordinates().getLatitude(),
                busService.getDistanceBetween(busModel.getCoordinates(), busStop.getCoordinates())
        ));

        return busDataAtMinuteLis;
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
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
//        Queue<Integer> queue = Collections.asLifoQueue(new LinkedList<>());
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue);

        queue.add(4);

        System.out.println(queue);

        queue.remove();
        System.out.println(queue);

        queue.poll();
        System.out.println(queue);

        Collections.reverse((List<?>) queue);
        System.out.println(queue);
    }
}
