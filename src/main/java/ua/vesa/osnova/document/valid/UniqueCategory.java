package ua.vesa.osnova.document.valid;

import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.document.service.CategoryService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategory implements ConstraintValidator<CategoryValid, String> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(CategoryValid categoryValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryService == null){
            return true;
        }
        return categoryService.getByTitle(s) == null;
    }
}
