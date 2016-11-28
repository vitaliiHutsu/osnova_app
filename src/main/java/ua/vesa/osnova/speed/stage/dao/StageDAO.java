package ua.vesa.osnova.speed.stage.dao;

import ua.vesa.osnova.speed.stage.model.Stage;

import java.util.List;
import java.util.Set;

public interface StageDAO{
    List<Stage> getAll();
    List<Stage> getAllOrderByDate();
    List<Stage> getStages(List<String> station);
    Stage getByStations(String firstStation, String secondStation);
    Stage getById(int id);
    void add(Stage stage);
    void addAll(List<List<String>> listStage);
    void update(Stage stage);
    void remove(Stage stage);
}
