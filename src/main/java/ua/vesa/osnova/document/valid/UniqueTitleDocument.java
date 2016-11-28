package ua.vesa.osnova.document.valid;


import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.document.dao.DocumentDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTitleDocument implements ConstraintValidator<DocumentTitleValid, String> {
    @Autowired
    private DocumentDao documentDao;
    @Override
    public void initialize(DocumentTitleValid documentTitleValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (documentDao == null)
            return true;
        return documentDao.getByTitle(s) == null;
    }
}
