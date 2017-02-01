package ua.vesa.osnova.speed.TRAStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vesa.osnova.speed.TRAStation.dao.TRADao;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TRAServiceImpl implements TRAService {
    @Autowired
    private TRADao traDao;

    @Override
    public void add(TRAModel model) {
        traDao.add(model);
    }

    @Override
    public TRAModel get(String title) {
        return traDao.get(title);
    }

    @Override
    public List<TRAModel> getAll() {
        return traDao.getAll();
    }

    @Override
    public TRAModel getTitleByIdStation(int id) {
        return traDao.getTitleByIdStation(id);
    }

    @Override
    public void remove(TRAModel traModel) {
        traDao.remove(traModel);
    }

    @Override
    public void update(TRAModel traModel) {
        traDao.update(traModel);
    }
}
