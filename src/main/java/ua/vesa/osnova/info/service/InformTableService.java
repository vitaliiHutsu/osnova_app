package ua.vesa.osnova.info.service;

import ua.vesa.osnova.info.model.InformTable;

import java.util.List;

public interface InformTableService {
    void add(InformTable informTable);

    List<InformTable> getAll();

    void remove(InformTable informTable);

    InformTable getByTitle(String title);

    InformTable getByUUID(String uuid);
    InformTable getById(int id);
}
