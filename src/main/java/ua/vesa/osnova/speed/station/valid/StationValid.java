package ua.vesa.osnova.speed.station.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueNameStationValid.class})
public @interface StationValid {
    String message() default "Есть такая станция!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
