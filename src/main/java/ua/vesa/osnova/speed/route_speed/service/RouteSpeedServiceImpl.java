package ua.vesa.osnova.speed.route_speed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.speed.route_speed.dao.RouteSpeedDAO;
import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;

import java.util.List;

@Service
@Transactional
public class RouteSpeedServiceImpl implements RouteSpeedService{
    @Autowired
    private RouteSpeedDAO routeSpeedDAO;

    @Override
    public void add(RouteSpeed routeSpeed) {
        routeSpeedDAO.add(routeSpeed);
    }

    @Override
    public RouteSpeed getById(int id) {
        return routeSpeedDAO.getById(id);
    }

    @Override
    public List<RouteSpeed> getAll() {
        return routeSpeedDAO.getAll();
    }

    @Override
    public void update(RouteSpeed routeSpeed) {
        routeSpeedDAO.update(routeSpeed);
    }

    @Override
    public void delete(RouteSpeed routeSpeed) {
        routeSpeedDAO.delete(routeSpeed);
    }

    @Override
    public RouteSpeed getByName(String title) {
        return routeSpeedDAO.getByName(title);
    }
}
