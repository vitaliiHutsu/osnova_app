package ua.vesa.osnova.schedule.dao;

import ua.vesa.osnova.schedule.model.Schedule;

import java.util.List;

public interface ScheduleDAO {
     void add(Schedule schedule);
     Schedule getById(int id);
     List<Schedule> getAll();
     void update(Schedule schedule);
     void delete(Schedule schedule);
     int getLastId();
}
