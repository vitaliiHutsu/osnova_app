package ua.vesa.osnova.info.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.info.model.InformTable;

import java.util.List;

@Repository
public class InformDaoImpl implements InformDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(InformTable informTable) {
        sessionFactory.getCurrentSession().save(informTable);
    }

    @Override
    public List<InformTable> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformTable.class);
        criteria.addOrder(Order.desc("date_add")).setMaxResults(10);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public void remove(InformTable informTable) {
        sessionFactory.getCurrentSession().delete(informTable);
    }

    @Override
    public InformTable getByTitle(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformTable.class);
        criteria.add(Restrictions.eq("title", title));
        if (criteria.list().size() > 0)
            return (InformTable) criteria.uniqueResult();
        return null;
    }

    @Override
    public InformTable getByUUID(String uuid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformTable.class);
        criteria.add(Restrictions.eq("uuid", uuid));

        if (criteria.list().size() > 0)
            return (InformTable) criteria.uniqueResult();
        return null;
    }

    @Override
    public InformTable getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InformTable.class);
        criteria.add(Restrictions.eq("id", id));

        if (criteria.list().size() > 0)
            return (InformTable) criteria.uniqueResult();
        return null;
    }
}
