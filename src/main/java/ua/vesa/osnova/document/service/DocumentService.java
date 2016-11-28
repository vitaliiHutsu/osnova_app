package ua.vesa.osnova.document.service;

import ua.vesa.osnova.document.model.Document;

import java.util.List;
import java.util.Set;

public interface DocumentService {
    void add(Document document);
    Set<Document> getAll();
    Document getById(int id);
    Document getByUUID(String uuid);
    void remove(Document document);
    Document getByTitle(String title);
    Document getByNumber(String number);
    List<Document> get10Items();
}
