package ua.vesa.osnova.document.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.document.dao.DocumentDao;
import ua.vesa.osnova.document.model.Document;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class DocumentDaoImpl implements DocumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Document document) {
        sessionFactory.getCurrentSession().save(document);
    }

    @Override
    public Set<Document> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("date_add"));
        if (criteria.list().size() > 0)
            return new LinkedHashSet<>(criteria.list());
        return null;
    }

    @Override
    public Document getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("id", id));
        if (criteria.list().size() > 0)
            return (Document) criteria.uniqueResult();
        return null;
    }

    @Override
    public Document getByUUID(String uuid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        if (criteria.list().size() > 0)
            return (Document) criteria.uniqueResult();
        return null;
    }

    @Override
    public void remove(Document document) {
        sessionFactory.getCurrentSession().delete(document);
    }

    @Override
    public Document getByTitle(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("title", title));
        if (criteria.list().size() > 0)
            return (Document) criteria.uniqueResult();
        return null;
    }

    @Override
    public Document getByNumber(String number) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("number_doc", number));
        if (criteria.list().size() > 0)
            return (Document) criteria.uniqueResult();
        return null;
    }

    @Override
    public List get10Items() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class);
        criteria.addOrder(Order.desc("date_add"));
        criteria.setMaxResults(10);
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }
}
