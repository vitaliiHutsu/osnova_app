package ua.vesa.osnova.user.service;

import ua.vesa.osnova.user.model.UserRole;

public interface UserRoleService {
    void add(UserRole userRole);
    void delete(UserRole userRole);
    void update(UserRole userRole);
}
