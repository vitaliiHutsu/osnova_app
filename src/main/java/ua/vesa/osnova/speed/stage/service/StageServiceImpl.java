package ua.vesa.osnova.speed.stage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.speed.stage.dao.StageDAO;
import ua.vesa.osnova.speed.stage.model.Stage;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StageServiceImpl implements StageService{
    @Autowired
    private StageDAO stageDAO;

    @Override
    public List<Stage> getAll() {
        return stageDAO.getAll();
    }

    @Override
    public void addAll(List<List<String>> stage) {
        stageDAO.addAll(stage);
    }

    @Override
    public Stage getByStations(String firstStation, String secondStation) {
        return stageDAO.getByStations(firstStation, secondStation);
    }

    @Override
    public List<Stage> getStages(List<String> station) {
        return stageDAO.getStages(station);
    }

    @Override
    public Stage getById(int id) {
        return stageDAO.getById(id);
    }

    @Override
    public void update(Stage stage) {
        stageDAO.update(stage);
    }

    @Override
    public List<Stage> getAllOrderByDate() {
        return stageDAO.getAllOrderByDate();
    }
}
