package ua.vesa.osnova.document.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.document.dao.CategoryDao;
import ua.vesa.osnova.document.model.Category;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public Set<Category> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        return new LinkedHashSet<>(criteria.list());
    }

    @Override
    public Category getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("id", id));
        if (criteria.list().size() > 0)
            return (Category) criteria.uniqueResult();
        return null;
    }

    @Override
    public void remove(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

    @Override
    public Category getByTitle(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("title", title));
        if (criteria.list().size() > 0)
            return (Category) criteria.uniqueResult();
        return null;
    }
}
