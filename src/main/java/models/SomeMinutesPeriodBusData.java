package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SomeMinutesPeriodBusData {
    private final String TsCode;
    private  List<BusDataAtMinute> busDataAtMinutes = new ArrayList<>();

    //constructor
    public SomeMinutesPeriodBusData(String tsCode, List<BusDataAtMinute> newBusDataAtMinutes) {
        TsCode = tsCode;
        Collections.reverse(newBusDataAtMinutes);
        busDataAtMinutes=newBusDataAtMinutes;
    }

    //getter
    public String getTsCode() {
        return TsCode;
    }

    public List<BusDataAtMinute> getBusDataAtMinutes() {
        return busDataAtMinutes;
    }
}
