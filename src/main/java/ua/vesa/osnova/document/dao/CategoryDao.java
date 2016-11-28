package ua.vesa.osnova.document.dao;

import ua.vesa.osnova.document.model.Category;

import java.util.Set;

public interface CategoryDao {
    void add(Category category);
    Set<Category> getAll();
    Category getById(int id);
    void remove(Category category);
    Category getByTitle(String title);
}
