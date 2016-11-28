package ua.vesa.osnova.speed.route_speed.service;

import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;

import java.util.List;

public interface RouteSpeedService {
    public void add(RouteSpeed routeSpeed);
    public RouteSpeed getById(int id);
    public List<RouteSpeed> getAll();
    public void update(RouteSpeed routeSpeed);
    public void delete(RouteSpeed routeSpeed);
    public RouteSpeed getByName(String title);
}
