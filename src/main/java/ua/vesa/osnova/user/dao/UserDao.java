package ua.vesa.osnova.user.dao;

import ua.vesa.osnova.user.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User findByUserName(String username);
    void add(User user);
    void remove(User user);
    User getByName(String username);
    User getByEMail(String email);
    List<User> getByNotAuthorization();
    void authorization(User user);
    Set<User> getAll();
    void updatePass(User user);
    User getById(int id);
}
