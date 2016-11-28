package ua.vesa.osnova.document.dao;

import ua.vesa.osnova.document.model.Document;

import java.util.List;
import java.util.Set;

public interface DocumentDao {
    void add(Document document);
    Set<Document> getAll();
    Document getById(int id);
    Document getByUUID(String uuid);
    void remove(Document document);
    Document getByTitle(String title);
    Document getByNumber(String number);
    List get10Items();
}
