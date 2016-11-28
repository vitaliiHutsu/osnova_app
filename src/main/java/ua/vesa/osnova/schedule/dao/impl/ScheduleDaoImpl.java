package ua.vesa.osnova.schedule.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.schedule.dao.ScheduleDAO;
import ua.vesa.osnova.schedule.model.Schedule;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Schedule schedule) {
        schedule.setDate_add_or_update(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().save(schedule);
    }

    @Override
    public Schedule getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Schedule.class);
        criteria.add(Restrictions.eq("id", id));
        return (Schedule) criteria.uniqueResult();
    }

    @Override
    public List<Schedule> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Schedule.class);
        criteria.addOrder(Order.asc("number_train"));
        return criteria.list();
    }

    @Override
    public void update(Schedule schedule) {
        schedule.setDate_add_or_update(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().saveOrUpdate(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        sessionFactory.getCurrentSession().delete(schedule);
    }

    @Override
    public int getLastId() {
        Schedule schedule;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Schedule.class);
        criteria.addOrder(Order.desc("id")).setMaxResults(1);
        schedule = (Schedule) criteria.uniqueResult();
        if (schedule == null){
            return 0;
        }
        return schedule.getId();
    }
}
