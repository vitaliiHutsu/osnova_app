package ua.vesa.osnova.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.user.dao.UserDao;
import ua.vesa.osnova.user.model.User;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public User getByEMail(String email) {
        return userDao.getByEMail(email);
    }

    @Override
    public List<User> getByNotAuthorization() {
        return userDao.getByNotAuthorization();
    }

    @Override
    public void authorization(User user) {
        userDao.authorization(user);
    }

    @Override
    public Set<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void updatePass(User user) {
        userDao.updatePass(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
}
