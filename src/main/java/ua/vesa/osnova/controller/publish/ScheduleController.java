package ua.vesa.osnova.controller.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.schedule.model.DepartureAndArrival;
import ua.vesa.osnova.schedule.model.Schedule;
import ua.vesa.osnova.schedule.service.ScheduleService;
import ua.vesa.osnova.speed.route_speed.service.RouteSpeedService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("schedule/index");
        modelAndView.addObject("schedules", scheduleService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("schedule/index");
        Schedule schedule = scheduleService.getById(id);
        if (schedule.getNumber_train() % 2 == 0) {
            List<DepartureAndArrival> tmp = schedule.getDepartureAndArrivals();
            Collections.reverse(tmp);
            schedule.setDepartureAndArrivals(tmp);
        }
        modelAndView.addObject("schedules", scheduleService.getAll());
        modelAndView.addObject("schedule", schedule);
        return modelAndView;
    }
}
