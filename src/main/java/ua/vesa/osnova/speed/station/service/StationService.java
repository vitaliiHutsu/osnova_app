package ua.vesa.osnova.speed.station.service;

import ua.vesa.osnova.speed.station.model.Station;

import java.util.List;
import java.util.Set;

public interface StationService {
     void add(Station station);
     void addAll(List<String> station);
     List<Station> getByDirection(List<String> stations);
     List<Station> getAll();
     Station getById(int id);
     void update(Station station);
     List<Station> getAllOrderByDate();
     Station getByName(String name);
     void delete(Station station);
}
