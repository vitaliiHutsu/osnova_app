package ua.vesa.osnova.document.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vesa.osnova.document.dao.DocumentDao;
import ua.vesa.osnova.document.model.Document;
import ua.vesa.osnova.document.service.DocumentService;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{
    @Autowired
    private DocumentDao documentDao;

    @Override
    public void add(Document document) {
        documentDao.add(document);
    }

    @Override
    public Set<Document> getAll() {
        return documentDao.getAll();
    }

    @Override
    public Document getById(int id) {
        return documentDao.getById(id);
    }

    @Override
    public Document getByUUID(String uuid) {
        return documentDao.getByUUID(uuid);
    }

    @Override
    public void remove(Document document) {
        documentDao.remove(document);
    }

    @Override
    public Document getByTitle(String title) {
        return documentDao.getByTitle(title);
    }

    @Override
    public Document getByNumber(String number) {
        return documentDao.getByNumber(number);
    }

    @Override
    public List<Document> get10Items() {
        return documentDao.get10Items();
    }
}
