package ua.vesa.osnova.speed.TRAStation.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;

import java.util.List;


@Repository
public class TRADaoImpl implements TRADao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(TRAModel traModel) {
        sessionFactory.getCurrentSession().save(traModel);
    }

    @Override
    public TRAModel get(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TRAModel.class);
        criteria.add(Restrictions.eq("title", title));
        if (criteria.list().size() > 0) {
            return (TRAModel) criteria.uniqueResult();
        }
        return null;
    }

    @Override
    public List<TRAModel> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TRAModel.class);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public TRAModel getTitleByIdStation(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TRAModel.class);
        criteria.add(Restrictions.eq("id_station", id));
        if (criteria.list().size() > 0)
            return (TRAModel) criteria.uniqueResult();
        return null;

    }

    @Override
    public void remove(TRAModel traModel) {
        sessionFactory.getCurrentSession().delete(traModel);
    }

    @Override
    public void update(TRAModel traModel) {
        sessionFactory.getCurrentSession().saveOrUpdate(traModel);
    }
}
