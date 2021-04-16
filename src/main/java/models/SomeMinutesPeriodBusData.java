package models;

import java.util.ArrayList;
import java.util.List;

public class SomeMinutesPeriodBusData {
    private final String TsCode;
    private  List<BusDataAtMinute> busDataAtMinutes = new ArrayList<>();

    //constructor
    public SomeMinutesPeriodBusData(String tsCode, List<BusDataAtMinute> busDataAtMinutes) {
        TsCode = tsCode;
        this.busDataAtMinutes = busDataAtMinutes;
    }

    //getter
    public String getTsCode() {
        return TsCode;
    }

    public List<BusDataAtMinute> getBusDataAtMinutes() {
        return busDataAtMinutes;
    }
}
