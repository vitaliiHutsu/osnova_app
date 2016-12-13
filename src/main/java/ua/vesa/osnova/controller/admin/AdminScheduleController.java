package ua.vesa.osnova.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.info.TableNameApp;
import ua.vesa.osnova.info.model.InformTable;
import ua.vesa.osnova.info.service.InformTableService;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.schedule.model.DepartureAndArrival;
import ua.vesa.osnova.schedule.model.RouteSchedule;
import ua.vesa.osnova.schedule.model.Schedule;
import ua.vesa.osnova.schedule.service.RouteTrainService;
import ua.vesa.osnova.schedule.service.ScheduleService;
import ua.vesa.osnova.speed.station.model.Station;
import ua.vesa.osnova.speed.station.service.StationService;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin/schedule")
public class AdminScheduleController {

    private RouteSchedule routeScheduleTmp;
    private List<Station> stations;
    private List<Station> dbStation;
    private List<DepartureAndArrival> departureAndArrivals;
    private int idEditRoute = 0;
    private Schedule editScheduleTmp;
    private InformTable informTable;
    @Autowired
    private InformTableService informTableService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RouteTrainService routeTrainService;

    private List<Station> stationTmp;
    @Autowired
    private UserService userService;
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        dbStation = null;
        departureAndArrivals = null;
        ModelAndView modelAndView = new ModelAndView("admin/schedule/index");
        modelAndView.addObject("route", new RouteSchedule());
        modelAndView.addObject("listRoute", routeTrainService.getAll());
        modelAndView.addObject("listSchedule", scheduleService.getAll());
        stations = new ArrayList<>();
        return modelAndView;
    }

    @RequestMapping(value = "/newroute", method = RequestMethod.POST)
    public ModelAndView newRoute(@ModelAttribute("route") @Valid RouteSchedule route, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/schedule/index");
            return modelAndView;
        }
        dbStation = null;
        dbStation = stationService.getAll();
        routeScheduleTmp = route;
        modelAndView.addObject("route_tmp", routeScheduleTmp);
        modelAndView.addObject("listStation", dbStation);
        modelAndView.addObject("new_station", new Station());
        modelAndView.setViewName("admin/schedule/addRoute");
        return modelAndView;
    }

    @RequestMapping(value = "/editRoute/{id}", method = RequestMethod.GET)
    public ModelAndView editRoute(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        idEditRoute = id;
        routeScheduleTmp = routeTrainService.getById(id);
        stations = routeScheduleTmp.getStations();
        dbStation = stationService.getAll();
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("dbStations", dbStation);
        modelAndView.addObject("routeEdit", routeScheduleTmp);
        modelAndView.addObject("position", new Integer(stations.size() + 1));
        modelAndView.addObject("newStation", new Station());
        modelAndView.setViewName("admin/schedule/editRoute");
        return modelAndView;
    }


    @RequestMapping(value = "/editRoute", method = RequestMethod.POST)
    public ModelAndView editRoute(@RequestParam("position") int position, @RequestParam("stationId") int stationId) {
        boolean flag = false;
        ModelAndView modelAndView = new ModelAndView();
        Station station = stationService.getById(stationId);
        addStation(position, flag, station);
        modelAndView.setViewName("redirect:/admin/schedule/editRoute/" + idEditRoute);
        return modelAndView;
    }

    @RequestMapping(value = "/editRoute/addNewStation", method = RequestMethod.POST)
    public ModelAndView addNewStationEditRoute(@RequestParam(value = "position1", required = true) Integer position,
                                               @ModelAttribute("newStation") @Valid Station station,

                                               BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = false;
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/schedule/editRoute");
            modelAndView.addObject("stations", stations);
            modelAndView.addObject("dbStations", dbStation);
            modelAndView.addObject("routeEdit", routeScheduleTmp);
            modelAndView.addObject("position", new Integer(stations.size() + 1));
            return modelAndView;
        }
        stationService.add(station);
        addStation(position, flag, station);
        modelAndView.setViewName("redirect:/admin/schedule/editRoute/" + idEditRoute);
        return modelAndView;

    }

    private void addStation(int position, boolean flag, Station station) {
        if (stations.size() != 0) {
            for (Station eq : stations) {
                if (flag == true)
                    break;
                flag = eq.getTitle_station().toLowerCase().equals(station.getTitle_station().toLowerCase());
            }
        }
        if (!flag) {
            stations.add(position - 1, station);
            routeScheduleTmp.setStations(stations);
            routeTrainService.update(routeScheduleTmp);
        }
    }

    @RequestMapping(value = "/deleteRoute/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRoute(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        routeTrainService.delete(routeTrainService.getById(id));
        modelAndView.setViewName("redirect:/admin/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/addstation", method = RequestMethod.GET)
    public ModelAndView addStation(@RequestParam("id") int id) {
        boolean flag = false;
        if (id == 0)
            flag = true;
        ModelAndView modelAndView = new ModelAndView();
        Station station = stationService.getById(id);

        if (station != null) {
            if (stations.size() != 0) {
                for (Station eqStation : stations) {
                    if (flag == true)
                        break;
                    flag = eqStation.getTitle_station().toLowerCase().equals(station.getTitle_station().toLowerCase());
                }
            }
        }
        if (!flag)
            stations.add(station);
        modelAndView.addObject("addStations", stations);
        modelAndView.addObject("route_tmp", routeScheduleTmp);
        modelAndView.addObject("listStation", dbStation);
        modelAndView.addObject("new_station", new Station());


        modelAndView.setViewName("admin/schedule/addRoute");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewStation", method = RequestMethod.POST)
    private ModelAndView addNewStation(@ModelAttribute("new_station") @Valid Station station, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("addStations", stations);
            modelAndView.addObject("route_tmp", routeScheduleTmp);
            modelAndView.addObject("listStation", dbStation);
            modelAndView.setViewName("admin/schedule/addRoute");
            return modelAndView;
        }
        System.out.println(station.getTitle_station());
        stationService.add(station);
        dbStation = stationService.getAll();
        stations.add(station);
        modelAndView.setViewName("redirect:/admin/schedule/addstation?id=0");
        return modelAndView;
    }

    @RequestMapping(value = "/removestation", method = RequestMethod.GET)
    public ModelAndView deleteStationFromRout(@RequestParam("index") int index) {
        ModelAndView modelAndView = new ModelAndView();
        stations.remove(index);
        System.out.println(stations.size());
        modelAndView.setViewName("redirect:/admin/schedule/addstation?id=0");
        return modelAndView;
    }

    @RequestMapping(value = "/editRoute/removestation", method = RequestMethod.GET)
    public ModelAndView deleteStationFromEditRout(@RequestParam("index") int index) {
        ModelAndView modelAndView = new ModelAndView();
        stations.remove(index);
        routeScheduleTmp = routeTrainService.getById(idEditRoute);
        routeScheduleTmp.setStations(stations);
        routeTrainService.update(routeScheduleTmp);
        modelAndView.setViewName("redirect:/admin/schedule/editRoute/" + idEditRoute);
        return modelAndView;

    }

    @RequestMapping(value = "/addNewRoute", method = RequestMethod.GET)
    public ModelAndView addNewRoute() {
        ModelAndView modelAndView = new ModelAndView();
        routeScheduleTmp.setStations(stations);
        routeTrainService.add(routeScheduleTmp);
        modelAndView.setViewName("redirect:/admin/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewSchedule", method = RequestMethod.GET)
    public ModelAndView addNewSchedule(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        RouteSchedule routeSchedule = routeTrainService.getById(id);
        departureAndArrivals = new ArrayList<>();
        List<Station> stationList = routeSchedule.getStations();
        for (int i = 0; i < stationList.size(); i++) {
            DepartureAndArrival departureAndArrival = new DepartureAndArrival();
            departureAndArrival.setStation(stationList.get(i));
            departureAndArrival.setPosition(i + 1);
            departureAndArrivals.add(departureAndArrival);
        }

        Schedule schedule = new Schedule();
        schedule.setDepartureAndArrivals(departureAndArrivals);
        modelAndView.addObject("schedule", schedule);
        modelAndView.addObject("flagUpdate", false);
        modelAndView.setViewName("admin/schedule/newSchedule");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewSchedule", method = RequestMethod.POST)
    public ModelAndView addNewSchedule(@ModelAttribute("schedule") Schedule schedule, BindingResult result,
                                       HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/schedule/newSchedule");
            return modelAndView;
        }
        if (schedule.getDepartureAndArrivals() != null) {
            for (int i = 0; i < schedule.getDepartureAndArrivals().size(); i++) {
                schedule.getDepartureAndArrivals().get(i).setStation(departureAndArrivals.get(i).getStation());
            }

            final String msg = "Добавлено новое расписание п.№ " + schedule.getNumber_train();
            informTable = new InformTable();
            informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
            informTable.setAction(msg);
            informTable.setName_table(TableNameApp.SCHEDULE_TABLE);
            scheduleService.add(schedule);

            informTable.setTitle(String.valueOf(scheduleService.getLastId()));
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

        }
        modelAndView.setViewName("redirect:/admin/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/editSchedule/{id}", method = RequestMethod.GET)
    public ModelAndView editSchedule(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        editScheduleTmp = scheduleService.getById(id);
        stationTmp = new ArrayList<>();

        for (DepartureAndArrival departureAndArrival : editScheduleTmp.getDepartureAndArrivals())
            stationTmp.add(departureAndArrival.getStation());

        modelAndView.addObject("schedule", editScheduleTmp);
        modelAndView.addObject("flagUpdate", true);
        modelAndView.setViewName("admin/schedule/newSchedule");
        return modelAndView;
    }

    @RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
    public ModelAndView editSchedule(@ModelAttribute("schedule") Schedule schedule, HttpServletRequest request) {
        for (int i = 0; i < schedule.getDepartureAndArrivals().size(); i++) {
            schedule.getDepartureAndArrivals().get(i).setStation(stationTmp.get(i));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/schedule");
        editScheduleTmp.setDepartureAndArrivals(schedule.getDepartureAndArrivals());
        editScheduleTmp.setNotation(schedule.getNotation());
        editScheduleTmp.setNumber_train(schedule.getNumber_train());
        scheduleService.update(editScheduleTmp);
        InformTable informTable = informTableService.getByTitle((String.valueOf(editScheduleTmp.getId())));
        if (informTable != null)
            informTableService.remove(informTable);
        informAdd(Boolean.valueOf(request.getParameter("sendMessage")));
        return modelAndView;

    }

    private void informAdd(boolean b) {
        informTable = new InformTable();
        final String msg = "изменения в расписании п. № " + editScheduleTmp.getNumber_train();
        informTable.setAction(msg);
        informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
        informTable.setTitle(String.valueOf(editScheduleTmp.getId()));
        informTable.setName_table(TableNameApp.SCHEDULE_TABLE);
        informTableService.add(informTable);

        if(b) {
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


    }

    @RequestMapping(value = "/deleteSchedule/{id}", method = RequestMethod.GET)
    public ModelAndView removeSchedule(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        informTable = informTableService.getByTitle(String.valueOf(id));
        if (informTable != null)
            informTableService.remove(informTable);

        scheduleService.delete(scheduleService.getById(id));
        modelAndView.setViewName("redirect:/admin/schedule");
        return modelAndView;
    }


}
