package ua.vesa.osnova.user.dao;

import ua.vesa.osnova.user.model.UserRole;

public interface UserRoleDao {
    void add(UserRole userRole);
    void delete(UserRole userRole);
    void update(UserRole userRole);

}
