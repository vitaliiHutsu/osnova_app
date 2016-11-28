package ua.vesa.osnova.schedule.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.schedule.dao.RouteTrainDAO;
import ua.vesa.osnova.schedule.model.RouteSchedule;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class RouteTrainDaoImpl implements RouteTrainDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(RouteSchedule routeSchedule) {
        routeSchedule.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().save(routeSchedule);
    }

    @Override
    public RouteSchedule getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSchedule.class);
        criteria.add(Restrictions.eq("id", id));
        return (RouteSchedule) criteria.uniqueResult();
    }

    @Override
    public RouteSchedule getByNameRoute(String nameRoute) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSchedule.class);
        criteria.add(Restrictions.eq("name_route", nameRoute));
        return (RouteSchedule) criteria.uniqueResult();
    }

    @Override
    public void update(RouteSchedule routeSchedule) {
        routeSchedule.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().saveOrUpdate(routeSchedule);
    }

    @Override
    public List getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSchedule.class);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public void delete(RouteSchedule routeSchedule) {
        sessionFactory.getCurrentSession().delete(routeSchedule);
    }
}
