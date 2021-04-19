package restApi;

import models.SomeMinutesPeriodBusData;

import java.util.ArrayList;
import java.util.List;

public class BusDataResponse {
    private final String status;
    private final Integer code;

    //get
    private List<SomeMinutesPeriodBusData> allBusData = new ArrayList<>();

    //get constructor
    public BusDataResponse(String status, Integer code, List<SomeMinutesPeriodBusData> allBusData) {
        this.status = status;
        this.code = code;
        this.allBusData = allBusData;
    }


    //getter
    public List<SomeMinutesPeriodBusData> getAllBusData() {
        return allBusData;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }
}
