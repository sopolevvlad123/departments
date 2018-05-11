package com.validator;

import net.sf.oval.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Validator {

    @Autowired
    private net.sf.oval.Validator ovalValidator;

    public  List<ConstraintViolation> getViolationsList(Object valObj) {
        System.out.println("oval valid  -- " + ovalValidator.validate(valObj));
        return ovalValidator.validate(valObj);
    }


}
