package ua.vesa.osnova.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.schedule.dao.ScheduleDAO;
import ua.vesa.osnova.schedule.model.Schedule;
import ua.vesa.osnova.schedule.service.ScheduleService;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDAO scheduleDAO;
    @Override
    public void add(Schedule schedule) {
        scheduleDAO.add(schedule);
    }

    @Override
    public Schedule getById(int id) {
        return scheduleDAO.getById(id);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleDAO.getAll();
    }

    @Override
    public void update(Schedule schedule) {
        scheduleDAO.update(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleDAO.delete(schedule);
    }

    @Override
    public int getLastId() {
        return scheduleDAO.getLastId();
    }
}
