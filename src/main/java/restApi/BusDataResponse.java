package restApi;

import models.SomeMinutesPeriodBusData;

import java.util.ArrayList;
import java.util.List;

public class BusDataResponse {
    private final String status;
    private final Integer code;

    //get bus
    private String TSCode;
    private SomeMinutesPeriodBusData oneBusData;

    //get
    private List<SomeMinutesPeriodBusData> allBusData = new ArrayList<>();

    //get constructor
    public BusDataResponse(String status, Integer code, List<SomeMinutesPeriodBusData> allBusData) {
        this.status = status;
        this.code = code;
        this.allBusData = allBusData;
    }

    //get bus constructor
    public BusDataResponse(String status, Integer code, String TSCode, SomeMinutesPeriodBusData oneBusData) {
        this.status = status;
        this.code = code;
        this.TSCode = TSCode;
        this.oneBusData = oneBusData;
    }

    //getter
    public List<SomeMinutesPeriodBusData> getAllBusData() {
        return allBusData;
    }

    public String getTSCode() {
        return TSCode;
    }

    public SomeMinutesPeriodBusData getOneBusData() {
        return oneBusData;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }
}
