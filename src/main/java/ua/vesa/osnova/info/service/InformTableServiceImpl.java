package ua.vesa.osnova.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.info.dao.InformDao;
import ua.vesa.osnova.info.model.InformTable;

import java.util.List;

@Service
@Transactional
public class InformTableServiceImpl implements InformTableService{
    @Autowired
    private InformDao informDao;

    @Override
    public void add(InformTable informTable) {
        informDao.add(informTable);
    }

    @Override
    public List<InformTable> getAll() {
        return informDao.getAll();
    }

    @Override
    public void remove(InformTable informTable) {
        informDao.remove(informTable);
    }

    @Override
    public InformTable getByTitle(String title) {
        return informDao.getByTitle(title);
    }

    @Override
    public InformTable getByUUID(String uuid) {
        return informDao.getByUUID(uuid);
    }

    @Override
    public InformTable getById(int id) {
        return informDao.getById(id);
    }
}
