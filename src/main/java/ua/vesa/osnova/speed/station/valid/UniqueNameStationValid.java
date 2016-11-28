package ua.vesa.osnova.speed.station.valid;

import org.springframework.beans.factory.annotation.Autowired;
import ua.vesa.osnova.speed.station.service.StationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameStationValid implements ConstraintValidator<StationValid, String>{
    @Autowired
    private StationService stationService;
    @Override
    public void initialize(StationValid stationValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (stationService == null){
            return true;
        }
        return stationService.getByName(s) == null;
    }
}
