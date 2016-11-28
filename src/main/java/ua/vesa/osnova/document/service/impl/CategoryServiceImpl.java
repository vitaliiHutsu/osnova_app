package ua.vesa.osnova.document.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.document.dao.CategoryDao;
import ua.vesa.osnova.document.model.Category;
import ua.vesa.osnova.document.service.CategoryService;

import java.util.Set;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public Set<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getById(int id) {
        return categoryDao.getById(id);
    }

    @Override
    public void remove(Category category) {
        categoryDao.remove(category);
    }

    @Override
    public Category getByTitle(String title) {
        return categoryDao.getByTitle(title);
    }
}
