package ua.vesa.osnova.speed.TRAStation.service;

import ua.vesa.osnova.speed.TRAStation.model.TRAModel;

import java.util.List;

public interface TRAService {
    void add(TRAModel model);
    TRAModel get(String title);
    List<TRAModel> getAll();
    TRAModel getTitleByIdStation(int id);
    void remove(TRAModel traModel);
    void update(TRAModel traModel);
}
