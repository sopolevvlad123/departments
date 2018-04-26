package com.validator;

import net.sf.oval.ConstraintViolation;

import java.util.List;

public class Validator {

    private net.sf.oval.Validator validator = new net.sf.oval.Validator();

    public  List<ConstraintViolation> getViolationsList(Object valObj) {
        return validator.validate(valObj);
    }


}
