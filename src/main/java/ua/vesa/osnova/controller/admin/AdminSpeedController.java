package ua.vesa.osnova.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.info.TableNameApp;
import ua.vesa.osnova.info.model.InformTable;
import ua.vesa.osnova.info.service.InformTableService;
//import ua.vesa.osnova.document.other_pdf.model.OtherDoc;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;
import ua.vesa.osnova.speed.TRAStation.service.TRAService;
import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;
import ua.vesa.osnova.speed.route_speed.service.RouteSpeedService;
import ua.vesa.osnova.speed.stage.model.Stage;
import ua.vesa.osnova.speed.stage.service.StageService;
import ua.vesa.osnova.speed.station.model.Station;
import ua.vesa.osnova.speed.station.service.StationService;
import ua.vesa.osnova.speed.utils.StageUtils;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;
import ua.vesa.osnova.utils.FileOIUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin/speed")
public class AdminSpeedController {
    @Autowired
    private StationService stationService;
    @Autowired
    private TRAService traService;
    private Station station;
    private List<Station> listStations;
    private List<Station> routeListStations;
    @Autowired
    private InformTableService informTableService;
    @Autowired
    private RouteSpeedService routeSpeedService;
    @Autowired
    private StageService stageService;
    private int idSelectRouteSpeedEdit;
    private InformTable informTable;
    private Stage stageUpdate;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserService userService;

    @Autowired
    private FileOIUtils fileOIUtils;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listStation", stationService.getAll());
        modelAndView.addObject("listRouteSpeed", routeSpeedService.getAll());
        station = null;
        listStations = null;
        routeListStations = new ArrayList<>();
        modelAndView.setViewName("admin/speed/index");
        return modelAndView;
    }

    @RequestMapping(value = "/addStation", method = RequestMethod.GET)
    public ModelAndView addNewStation() {
        ModelAndView modelAndView = new ModelAndView("admin/speed/addNewStation");
        modelAndView.addObject("flagEdit", false);
        modelAndView.addObject("station", new Station());
        return modelAndView;
    }

    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
    public ModelAndView addNewStation(@ModelAttribute("station") @Valid Station station, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/speed/addNewStation");
            return modelAndView;
        }
        stationService.add(station);
        modelAndView.setViewName("redirect:/admin/speed");
        return modelAndView;
    }

    @RequestMapping(value = "/editStation/{id}", method = RequestMethod.GET)
    public ModelAndView editStation(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("admin/speed/addNewStation");
        station = stationService.getById(id);
        modelAndView.addObject("station", station);
        modelAndView.addObject("flagEdit", true);
        return modelAndView;
    }

    @RequestMapping(value = "/editStation", method = RequestMethod.POST)
    public ModelAndView editStation(@ModelAttribute("station") Station station, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        this.station.setNotation(station.getNotation());
        this.station.setSpeed_chief_even_pass(station.getSpeed_chief_even_pass());
        this.station.setSpeed_chief_even_freight(station.getSpeed_chief_even_freight());
        this.station.setSpeed_chief_odd_freight(station.getSpeed_chief_odd_freight());
        this.station.setSpeed_chief_odd_pass(station.getSpeed_chief_odd_pass());
        this.station.setBranch_track(station.getBranch_track());

        informTable = informTableService.getByTitle(this.station.getTitle_station());
        if (informTable != null)
            informTableService.remove(informTable);

        final String msg = "Изменения по ст. " + this.station.getTitle_station();
        informTable = new InformTable();
        informTable.setAction(msg);
        informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
        informTable.setName_table(TableNameApp.STATION_SPEED_TABLE);
        informTable.setTitle(this.station.getTitle_station());
        informTableService.add(informTable);
        if (Boolean.valueOf(request.getParameter("sendMessage"))) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : userService.getAll()) {
                        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
                    }
                }
            });
            thread.start();
        }
        stationService.update(this.station);
        modelAndView.setViewName("redirect:/admin/speed");
        return modelAndView;
    }

//    @RequestMapping(value = "/deleteStation", method = RequestMethod.POST)
//    public ModelAndView deleteStation(@ModelAttribute("station") Station station) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/admin/speed");
//        stationService.delete(this.station);
//        return modelAndView;
//    }

    @RequestMapping(value = "/newRouteSpeed", method = RequestMethod.GET)
    public ModelAndView newRouteSpeed() {
        ModelAndView modelAndView = new ModelAndView();
        if (listStations == null)
            listStations = stationService.getAll();
        returnRouteSpeed(modelAndView);
        return modelAndView;
    }

    private void returnRouteSpeed(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/speed/newRouteSpeed");
        modelAndView.addObject("listSelectStation", routeListStations);
        modelAndView.addObject("listStation", listStations);
    }

    @RequestMapping(value = "/selectStationRoute/{id}", method = RequestMethod.GET)
    public ModelAndView selectStationRoute(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = false;
        Station eqStation = stationService.getById(id);
        if (routeListStations.size() == 0) {
            routeListStations.add(eqStation);
        } else {
            for (Station station1 : routeListStations) {
                if (station1.getTitle_station().equals(eqStation.getTitle_station()))
                    flag = true;
            }
            if (!flag) {
                routeListStations.add(eqStation);
            }
        }

        modelAndView.setViewName("redirect:/admin/speed/newRouteSpeed");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewRoute", method = RequestMethod.GET)
    public ModelAndView addNewRoute() {
        String message;
        ModelAndView modelAndView = new ModelAndView();
        if (routeListStations.size() == 0 || routeListStations.size() == 1) {
            message = "Минимум две станции!!!";
            modelAndView.addObject("message", message);
            returnRouteSpeed(modelAndView);
        } else {
            String titleRouteSpeed = routeListStations.get(0).getTitle_station() + " - " + routeListStations.get(routeListStations.size() - 1).getTitle_station();
            if (routeSpeedService.getByName(titleRouteSpeed) == null) {
                RouteSpeed routeSpeed = new RouteSpeed();
                routeSpeed.setStations(routeListStations);
                routeSpeed.setTitle_route_speed(titleRouteSpeed);
                routeSpeedService.add(routeSpeed);

                StageUtils stageUtils = new StageUtils(routeListStations, stageService);
                stageUtils.addStageDB();
                modelAndView.setViewName("redirect:/admin/speed");
            } else {
                message = "Есть такой участок!!!";
                modelAndView.addObject("message", message);
                returnRouteSpeed(modelAndView);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editRouteSpeed/{id}", method = RequestMethod.GET)
    public ModelAndView editRoute(@PathVariable("id") int id) {
        idSelectRouteSpeedEdit = id;
        ModelAndView modelAndView = new ModelAndView("admin/speed/editRouteSpeed");
        RouteSpeed routeSpeed = routeSpeedService.getById(id);
        StageUtils stageUtils = new StageUtils(routeSpeed.getStations(), stageService);
        List<Stage> stages = stageService.getStages(stageUtils.getStationStr());
        modelAndView.addObject("listRouteUpdate", routeSpeed.getStations());
        modelAndView.addObject("routeSpeed", routeSpeed);
        modelAndView.addObject("listStation", stationService.getAll());
        modelAndView.addObject("listStage", stages);
        return modelAndView;
    }

    @RequestMapping(value = "/editStage/{id}", method = RequestMethod.GET)
    public ModelAndView editStage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        stageUpdate = stageService.getById(id);
        modelAndView.setViewName("admin/speed/editStage");
        modelAndView.addObject("stage", stageUpdate);
        return modelAndView;
    }

    @RequestMapping(value = "/editStage/{id}", method = RequestMethod.POST)
    public ModelAndView editStage(@ModelAttribute("stage") Stage stage, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/speed/editRouteSpeed/" + idSelectRouteSpeedEdit);

        informTable = informTableService.getByTitle(String.valueOf(stageUpdate.getStage_uniq()));
        if (informTable != null)
            informTableService.remove(informTable);

        final String msg = "Изменения по перегону " + stageUpdate.getStartStation() + " - " + stageUpdate.getEndStation();
        informTable = new InformTable();
        informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
        informTable.setAction(msg);
        informTable.setName_table(TableNameApp.STAGE_SPEED_TABLE);
        informTable.setTitle(String.valueOf(stageUpdate.getStage_uniq())); //Хранения title в таблице inform stage по уникальному ключу uniq_station
        informTableService.add(informTable);

        if (Boolean.valueOf(request.getParameter("sendMessage"))) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : userService.getAll()) {
                        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
                    }
                }
            });
            thread.start();
        }
        stageService.update(stage);
        return modelAndView;
    }

    @RequestMapping(value = "/newStationOnRouteSpeed", method = RequestMethod.GET)
    public ModelAndView addNewStationInRouteSpeed(@RequestParam("stationId") int id,
                                                  @RequestParam("optionsRadios") String position) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/speed/editRouteSpeed/" + idSelectRouteSpeedEdit);

        if (id != 0) {
            RouteSpeed routeSpeed = routeSpeedService.getById(idSelectRouteSpeedEdit);
            Station addStation = stationService.getById(id);
            if (position.equals("start"))
                routeSpeed.getStations().add(0, addStation);
            else
                routeSpeed.getStations().add(addStation);

            routeSpeed.setTitle_route_speed(routeSpeed.getStations().get(0).getTitle_station() + " - " +
                    routeSpeed.getStations().get(routeSpeed.getStations().size() - 1).getTitle_station());

            StageUtils stageUtils = new StageUtils(routeSpeed.getStations(), stageService);
            stageUtils.addStageDB();
            routeSpeedService.update(routeSpeed);


        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteRouteSpeed/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRouteSpeed(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        routeSpeedService.delete(routeSpeedService.getById(id));
        modelAndView.setViewName("redirect:/admin/speed");
        return modelAndView;
    }

    @RequestMapping(value = "/newPdfSpeed", method = RequestMethod.GET)
    public ModelAndView newPdf() {
        ModelAndView modelAndView = new ModelAndView("admin/other/newOtherDoc");
//        modelAndView.addObject("otherDoc", new OtherDoc());
        return modelAndView;
    }

    @RequestMapping(value = "/deleteStationList/{index}", method = RequestMethod.GET)
    public ModelAndView deleteStationList(@PathVariable("index") int index) {
        routeListStations.remove(index);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/speed/newRouteSpeed");
        return modelAndView;
    }

//    @RequestMapping(value = "addImgStation", method = RequestMethod.POST)
//    public @ResponseBody String addImgStation(@RequestParam(value = "fileIMG", required = false) Part fileIMG,
//                                              HttpServletRequest req){
//
//
//        if (fileIMG.getContentType().equals("image/jpeg") || fileIMG.getContentType().equals("image/png")){
//
//            String uuid = UUID.randomUUID().toString() + ".jpg";
//            fileOIUtils.saveDataDoc(uuid, fileIMG, IMG_STATION_PATH);
//            Station station = stationService.getById(Integer.valueOf(req.getParameter("idStation")));
//            TRAModel traModel = new TRAModel();
//            traModel.setStation(station);
//            traModel.setTitle(uuid);
//            traModel.setData(new Date().getTime());
//            traService.add(traModel);
//            System.out.println("OOOKKK");
//
//        }else {
//            return "error";
//        }
//        return "ok";
//    }
}
