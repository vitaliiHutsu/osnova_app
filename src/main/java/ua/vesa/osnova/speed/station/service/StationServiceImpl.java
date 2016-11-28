package ua.vesa.osnova.speed.station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.speed.station.dao.StationDAO;
import ua.vesa.osnova.speed.station.model.Station;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StationServiceImpl implements StationService{
    @Autowired
    StationDAO stationDAO;

    @Override
    public void add(Station station) {
        stationDAO.add(station);
    }

    @Override
    public void addAll(List<String> station) {
        stationDAO.addAll(station);
    }

    @Override
    public List<Station> getByDirection(List<String> stations) {
        return stationDAO.getByDirection(stations);
    }

    @Override
    public List<Station> getAll() {
        return stationDAO.getAll();
    }

    @Override
    public Station getById(int id) {
        return stationDAO.getById(id);
    }

    @Override
    public void update(Station station) {
        stationDAO.update(station);
    }

    @Override
    public List<Station> getAllOrderByDate() {
        return stationDAO.getAllOrderByDate();
    }

    @Override
    public Station getByName(String name) {
        return stationDAO.getByName(name);
    }

    @Override
    public void delete(Station station) {
        stationDAO.delete(station);
    }
}
