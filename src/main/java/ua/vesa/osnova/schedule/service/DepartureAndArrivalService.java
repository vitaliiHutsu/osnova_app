package ua.vesa.osnova.schedule.service;


import ua.vesa.osnova.schedule.model.DepartureAndArrival;

public interface DepartureAndArrivalService {
    public void add(DepartureAndArrival departureAndArrival);
    public DepartureAndArrival getById(int id);
}
