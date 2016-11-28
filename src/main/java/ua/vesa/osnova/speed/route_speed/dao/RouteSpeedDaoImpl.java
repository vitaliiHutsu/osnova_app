package ua.vesa.osnova.speed.route_speed.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;

import java.util.GregorianCalendar;
import java.util.List;
@Repository
public class RouteSpeedDaoImpl implements RouteSpeedDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add(RouteSpeed routeSpeed) {
        routeSpeed.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().save(routeSpeed);
    }

    @Override
    public RouteSpeed getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSpeed.class);
        criteria.add(Restrictions.eq("id", id));
        return (RouteSpeed) criteria.uniqueResult();
    }

    @Override
    public List<RouteSpeed> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSpeed.class);
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void update(RouteSpeed routeSpeed) {
        routeSpeed.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().saveOrUpdate(routeSpeed);
    }

    @Override
    public void delete(RouteSpeed routeSpeed) {
        sessionFactory.getCurrentSession().delete(routeSpeed);
    }

    @Override
    public RouteSpeed getByName(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RouteSpeed.class);
        criteria.add(Restrictions.eq("title_route_speed", title));
        return (RouteSpeed) criteria.uniqueResult();
    }
}
