package ua.vesa.osnova.speed.route_speed.valid;

import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.speed.route_speed.service.RouteSpeedService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameRouteSpeed implements ConstraintValidator<RouteSpeedValid, String> {
    @Autowired
    private RouteSpeedService routeSpeedService;

    @Override
    public void initialize(RouteSpeedValid routeSpeedValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (routeSpeedService == null)
            return true;
        return routeSpeedService.getByName(s) == null;
    }
}
