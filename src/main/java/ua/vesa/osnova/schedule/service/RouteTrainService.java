package ua.vesa.osnova.schedule.service;

import ua.vesa.osnova.schedule.model.RouteSchedule;

import java.util.List;

public interface RouteTrainService {
    public void add(RouteSchedule routeSchedule);
    public RouteSchedule getById(int id);
    public RouteSchedule getByNameRoute(String nameRoute);
    public void update(RouteSchedule routeSchedule);
    public List<RouteSchedule> getAll();
    public void delete(RouteSchedule routeSchedule);
}
