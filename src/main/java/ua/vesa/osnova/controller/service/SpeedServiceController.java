package ua.vesa.osnova.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.vesa.osnova.speed.station.model.Station;
import ua.vesa.osnova.speed.station.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/service/speed")
public class SpeedServiceController {
    @Autowired
    private StationService stationService;

    @RequestMapping(value = "station", headers = "Accept=application/json", method = RequestMethod.GET)
    public @ResponseBody List<Station> allRoute() {
//        List<Station> stations = stationService.getAll();
        return null;
    }
}
