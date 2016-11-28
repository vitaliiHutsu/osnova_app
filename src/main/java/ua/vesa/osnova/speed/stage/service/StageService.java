package ua.vesa.osnova.speed.stage.service;

import ua.vesa.osnova.speed.stage.model.Stage;

import java.util.List;
import java.util.Set;

public interface StageService {
    public List<Stage> getAll();
    public void addAll(List<List<String>> stage);
    public Stage getByStations(String firstStation, String secondStation);
    public List<Stage> getStages(List<String> station);
    public Stage getById(int id);
    public void update(Stage stage);
    public List<Stage> getAllOrderByDate();
}
