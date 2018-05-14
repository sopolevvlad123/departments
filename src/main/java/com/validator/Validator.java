package com.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.integration.spring.BeanInjectingCheckInitializationListener;
import org.springframework.stereotype.Component;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;

import java.util.List;

@Component
public class Validator {
    private static net.sf.oval.Validator validator;

    static {
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(BeanInjectingCheckInitializationListener.INSTANCE);
        validator = new net.sf.oval.Validator(myConfigurer);
    }


    public  static List<ConstraintViolation> getViolationsList(Object valObj) {
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(BeanInjectingCheckInitializationListener.INSTANCE);
        validator = new net.sf.oval.Validator(myConfigurer);
        return validator.validate(valObj);
    }


}
