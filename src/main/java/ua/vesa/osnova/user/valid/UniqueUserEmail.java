package ua.vesa.osnova.user.valid;


import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmail implements ConstraintValidator<UserEmailValid, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserEmailValid userEmailValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userService == null)
            return true;

        return userService.getByEMail(s) == null;
    }
}
