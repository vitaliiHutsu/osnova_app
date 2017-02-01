package ua.vesa.osnova.speed.TRAStation.dao;

import ua.vesa.osnova.speed.TRAStation.model.TRAModel;

import java.util.List;

public interface TRADao {
    void add(TRAModel traModel);
    TRAModel get(String title);
    List<TRAModel> getAll();
    TRAModel getTitleByIdStation(int id);
    void remove(TRAModel traModel);
    void update(TRAModel traModel);
}
