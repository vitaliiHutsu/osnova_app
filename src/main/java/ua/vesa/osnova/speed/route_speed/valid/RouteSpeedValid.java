package ua.vesa.osnova.speed.route_speed.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueNameRouteSpeed.class})
public @interface RouteSpeedValid {
    String message() default "Есть такой маршрут!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
