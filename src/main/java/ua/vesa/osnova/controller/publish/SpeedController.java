package ua.vesa.osnova.controller.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.controller.admin.AdminSpeedController;
import ua.vesa.osnova.controller.admin.service.TraStationService;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;
import ua.vesa.osnova.speed.TRAStation.service.TRAService;
import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;
import ua.vesa.osnova.speed.route_speed.service.RouteSpeedService;
import ua.vesa.osnova.speed.stage.model.Stage;
import ua.vesa.osnova.speed.stage.service.StageService;
import ua.vesa.osnova.speed.station.model.Station;
import ua.vesa.osnova.speed.station.service.StationService;
import ua.vesa.osnova.speed.utils.StageUtils;
import ua.vesa.osnova.utils.FileOIUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/speed")
public class SpeedController {

    @Autowired
    private RouteSpeedService routeSpeedService;
    @Autowired
    private StageService stageService;

    private List<RouteSpeed> routeSpeedList;
    @Autowired
    private StationService stationService;
    @Autowired
    private FileOIUtils fileOIUtils;

    private boolean flag;

    @Autowired
    private TRAService traService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("speed/index");
        routeSpeedList = routeSpeedService.getAll();
        modelAndView.addObject("routeSpeedList", routeSpeedList);
        modelAndView.addObject("stageListOrderByDate", stageService.getAllOrderByDate());
        modelAndView.addObject("stationListOrderByDate", stationService.getAllOrderByDate());
        return modelAndView;
    }

    @RequestMapping(value = "/odd/{id}")
    public ModelAndView routeOdd(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("speed/index");
        flag = true;
        RouteSpeed routeSpeed = routeSpeedService.getById(id);
        StageUtils stageUtils = new StageUtils(routeSpeed.getStations(), null);
        modelAndView.addObject("routeSpeed", routeSpeed);
        modelAndView.addObject("stageList", stageService.getStages(stageUtils.getStationStr()));
        modelAndView.addObject("routeSpeedList", routeSpeedList);
        modelAndView.addObject("flag", flag);
        return modelAndView;
    }

    @RequestMapping(value = "/even/{id}")
    public ModelAndView routeEven(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("speed/index");
        flag = false;
        RouteSpeed routeSpeed = routeSpeedService.getById(id);
        List<Station> stations = routeSpeed.getStations();
        Collections.reverse(stations);
        routeSpeed.setStations(stations);
        StageUtils stageUtils = new StageUtils(routeSpeed.getStations(), null);
        List<Stage> stages = stageService.getStages(stageUtils.getStationStr());
//        Collections.reverse(stages);
        modelAndView.addObject("routeSpeed", routeSpeed);
        modelAndView.addObject("stageList", stages);
        modelAndView.addObject("routeSpeedList", routeSpeedList);
        modelAndView.addObject("flag", flag);
        return modelAndView;
    }

    @RequestMapping(value = "/imageStation/{path}", method = RequestMethod.GET)
    public void getImgStation(@PathVariable("path") String path, HttpServletResponse response){
        fileOIUtils.streamReport(response, fileOIUtils.getDataDoc((path+".jpg"), TraStationService.IMG_STATION_PATH), path, false);
    }

    @RequestMapping(value = "/service/traStation/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    TRAModel get(@PathVariable int id) {
          return traService.getTitleByIdStation(id);

    }
}
