package ua.vesa.osnova.document.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueTitleDocument.class})
public @interface DocumentTitleValid {
    String message() default "Есть такой документ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
