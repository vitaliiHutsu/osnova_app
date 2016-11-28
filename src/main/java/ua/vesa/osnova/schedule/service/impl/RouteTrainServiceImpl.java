package ua.vesa.osnova.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.schedule.dao.RouteTrainDAO;
import ua.vesa.osnova.schedule.model.RouteSchedule;
import ua.vesa.osnova.schedule.service.RouteTrainService;

import java.util.List;

@Service
@Transactional
public class RouteTrainServiceImpl implements RouteTrainService {
    @Autowired
    private RouteTrainDAO routeTrainDAO;
    @Override
    public void add(RouteSchedule routeSchedule) {
        routeTrainDAO.add(routeSchedule);
    }

    @Override
    public RouteSchedule getById(int id) {
        return routeTrainDAO.getById(id);
    }

    @Override
    public RouteSchedule getByNameRoute(String nameRoute) {
        return routeTrainDAO.getByNameRoute(nameRoute);
    }

    @Override
    public void update(RouteSchedule routeSchedule) {
        routeTrainDAO.update(routeSchedule);
    }

    @Override
    public List<RouteSchedule> getAll() {
        return routeTrainDAO.getAll();
    }

    @Override
    public void delete(RouteSchedule routeSchedule) {
        routeTrainDAO.delete(routeSchedule);
    }
}
