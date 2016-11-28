package ua.vesa.osnova.schedule.dao;


import ua.vesa.osnova.schedule.model.RouteSchedule;

import java.util.List;

public interface RouteTrainDAO {
    public void add(RouteSchedule routeSchedule);
    public RouteSchedule getById(int id);
    public RouteSchedule getByNameRoute(String nameRoute);
    public void update(RouteSchedule routeSchedule);
    public List getAll();
    public void delete(RouteSchedule routeSchedule);
}
