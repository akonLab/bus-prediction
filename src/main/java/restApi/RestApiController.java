package restApi;

import models.SomeMinutesPeriodBusData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get10min")
public class RestApiController {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

//
//    @GetMapping
//    public BusDataResponse showAllBuses() {
//        return new BusDataResponse( SUCCESS_STATUS, CODE_SUCCESS,busContr.getBusModelHashMap());
//
//    }
//
//    @GetMapping("/bus")
//    public BusDataResponse showOnlyOneBusByTSCode(@RequestParam(value = "busTSCode") String busTSCode, @RequestBody BusDataRequest request) {
//        final BusDataResponse response;
//
//        if (busContr.getBusModelHashMap().containsKey(busTSCode)) {
//            SomeMinutesPeriodBusData currentBus=busContr.getBusModelHashMap().get(busTSCode);
//            response = new BusDataResponse( SUCCESS_STATUS, CODE_SUCCESS,currentBus.getTsCode(),currentBus);
//        } else {
//            response = new BusDataResponse(ERROR_STATUS, AUTH_FAILURE,"not found",null);
//        }
//        return response;
    //}
}
