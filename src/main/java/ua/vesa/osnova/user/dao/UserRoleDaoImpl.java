package ua.vesa.osnova.user.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.user.model.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao{
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(UserRole userRole) {
        sessionFactory.getCurrentSession().save(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        sessionFactory.getCurrentSession().save(userRole);
    }

    @Override
    public void update(UserRole userRole) {
        sessionFactory.getCurrentSession().saveOrUpdate(userRole);
    }

}
