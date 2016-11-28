package ua.vesa.osnova.schedule.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.schedule.dao.DepartureAndArrivalDAO;
import ua.vesa.osnova.schedule.model.DepartureAndArrival;

@Repository
public class DepartureAndArrivalDaoImpl implements DepartureAndArrivalDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add(DepartureAndArrival departureAndArrival) {
        sessionFactory.getCurrentSession().save(departureAndArrival);
    }

    @Override
    public DepartureAndArrival getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartureAndArrival.class);
        criteria.add(Restrictions.eq("id", id));
        return (DepartureAndArrival) criteria.uniqueResult();
    }
}
