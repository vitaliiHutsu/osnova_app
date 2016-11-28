package ua.vesa.osnova.user.valid;


import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserName implements ConstraintValidator<UserNameValid, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserNameValid userNameValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (userService == null)
            return true;
        return userService.getByName(s) == null;
    }
}
