package ua.vesa.osnova.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.schedule.dao.DepartureAndArrivalDAO;
import ua.vesa.osnova.schedule.model.DepartureAndArrival;
import ua.vesa.osnova.schedule.service.DepartureAndArrivalService;

@Service
@Transactional
public class DepartureAndArrivalServiceImpl implements DepartureAndArrivalService {
    @Autowired
    private DepartureAndArrivalDAO departureAndArrivalDAO;
    @Override
    public void add(DepartureAndArrival departureAndArrival) {
        departureAndArrivalDAO.add(departureAndArrival);
    }

    @Override
    public DepartureAndArrival getById(int id) {
        return departureAndArrivalDAO.getById(id);
    }
}
