package ua.vesa.osnova.document.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueCategory.class})
public @interface CategoryValid {
    String message() default "Есть такая категория!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
