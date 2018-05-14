package com.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.integration.spring.BeanInjectingCheckInitializationListener;
import org.springframework.stereotype.Component;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import java.util.List;

@Component
public class Validator {


    public List<ConstraintViolation> getViolationsList(Object valObj) {
        net.sf.oval.Validator validator;
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(BeanInjectingCheckInitializationListener.INSTANCE);
        validator = new net.sf.oval.Validator(myConfigurer);
        System.out.println(" my configurer " + myConfigurer);
        System.out.println("my validator " + validator.getConfigurers());

        System.out.println("oval valid  -- " + validator.validate(valObj));
        return validator.validate(valObj);
    }


}
