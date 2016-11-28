package ua.vesa.osnova.speed.route_speed.dao;

import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;

import java.util.List;

public interface RouteSpeedDAO {
    public void add(RouteSpeed routeSpeed);
    public RouteSpeed getById(int id);
    public List<RouteSpeed> getAll();
    public void update(RouteSpeed routeSpeed);
    public void delete(RouteSpeed routeSpeed);
    public RouteSpeed getByName(String title);
}
