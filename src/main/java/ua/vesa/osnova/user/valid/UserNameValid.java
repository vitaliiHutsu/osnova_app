package ua.vesa.osnova.user.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueUserName.class})
public @interface UserNameValid {
    String message() default "Есть такой user!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
