package ua.vesa.osnova.user.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.model.UserRole;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUserName(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        if (criteria.list().size() > 0) {
            return (User) criteria.uniqueResult();
        }
        return null;
    }

    @Override
    public void add(User user) {
        UserRole userRole = new UserRole(user, "ROLE_USER");
        Set<UserRole> userRoles = new LinkedHashSet<>();
        userRoles.add(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRoles(userRoles);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void remove(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getByName(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        if (criteria.list().size() > 0)
            return (User) criteria.uniqueResult();
        return null;
    }

    @Override
    public User getByEMail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        if (criteria.list().size() > 0)
            return (User) criteria.uniqueResult();
        return null;

    }

    @Override
    public List<User> getByNotAuthorization() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("enabled", false));
        criteria.addOrder(Order.desc("date_register"));
        if (criteria.list().size() > 0)
            return criteria.list();
        return null;
    }

    @Override
    public void authorization(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Set<User> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("enabled", true));
        criteria.addOrder(Order.desc("date_register"));
        if (criteria.list().size() > 0) {
            return new LinkedHashSet<>(criteria.list());
        }
        return null;
    }

    @Override
    public void updatePass(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("user_id", id));

        return (User) criteria.uniqueResult();
    }

}
