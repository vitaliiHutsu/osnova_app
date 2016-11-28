package ua.vesa.osnova.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.user.dao.UserRoleDao;
import ua.vesa.osnova.user.model.UserRole;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void add(UserRole userRole) {
        userRoleDao.add(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleDao.delete(userRole);
    }

    @Override
    public void update(UserRole userRole) {
        userRoleDao.update(userRole);
    }

}
