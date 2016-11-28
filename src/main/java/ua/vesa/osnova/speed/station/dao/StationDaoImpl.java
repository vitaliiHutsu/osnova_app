package ua.vesa.osnova.speed.station.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.speed.station.model.Station;

import java.util.*;

@Repository
public class StationDaoImpl implements StationDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Station station) {
        station.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().saveOrUpdate(station);
    }

    @Override
    public void addAll(List<String> station) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("INSERT IGNORE INTO station_speed (title_station) VALUES (:title_station)");
        for (String s : station) {
            query.setParameter("title_station", s);
            query.executeUpdate();
        }
    }

    @Override
    public List<Station> getByDirection(List<String> stations) {
        List<Station> list = new ArrayList<>();
        Station station = null;
        for (String s : stations) {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
            criteria.add(Restrictions.eq("title_station", s));
            station = (Station) criteria.uniqueResult();
            list.add(station);
        }
        return list;
    }

    @Override
    public List<Station> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
        criteria.addOrder(Order.asc("title_station"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public Station getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
        criteria.add(Restrictions.eq("id", id));
        if (criteria.list().size() > 0)
            return (Station) criteria.uniqueResult();
        return null;
    }

    @Override
    public void update(Station station) {
        Session session = sessionFactory.getCurrentSession();
        station.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        session.saveOrUpdate(station);
    }

    @Override
    public List<Station> getAllOrderByDate() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setMaxResults(15);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public Station getByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Station.class);
        criteria.add(Restrictions.eq("title_station", name));
        if (criteria.list().size() > 0)
            return (Station) criteria.uniqueResult();
        return null;
    }

    @Override
    public void delete(Station station) {
        sessionFactory.getCurrentSession().delete(station);
    }
}
