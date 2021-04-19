package restApi;

import controllers.TimeController;
import models.SomeMinutesPeriodBusData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/get10min")
public class RestApiController {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

    private final TimeController timeController = new TimeController();
    private List<SomeMinutesPeriodBusData> allBusData=null;
    @GetMapping
    public BusDataResponse showAllBuses() {
        if (allBusData==null){
            allBusData=timeController.getAllBusData();

        }
        return new BusDataResponse(SUCCESS_STATUS, CODE_SUCCESS, allBusData);

    }

}
