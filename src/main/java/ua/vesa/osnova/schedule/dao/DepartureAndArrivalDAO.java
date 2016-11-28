package ua.vesa.osnova.schedule.dao;


import ua.vesa.osnova.schedule.model.DepartureAndArrival;

public interface DepartureAndArrivalDAO {
    public void add(DepartureAndArrival departureAndArrival);
    public DepartureAndArrival getById(int id);

}
