package com.utils;

import com.validator.Validator;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ConstraintViolationsParser {
    @Autowired
    private  Validator validator;

    public  Map<String,String> getViolationsMap(Object object) {
        System.out.println("sout from ConstraintViolationsParser " + object);
        System.out.println("sout from cons valid obj" +  validator);

        Map<String, String> violationMap = new HashMap<>();
        List<ConstraintViolation> violations = validator.getViolationsList(object);
        if (violations.size() > 0) {
            FieldContext fieldContext;
            for (int i = violations.size() - 1; i >= 0; i--) {
                fieldContext =  (FieldContext)violations.get(i).getCheckDeclaringContext();
                violationMap.put(fieldContext.getField().getName(),violations.get(i).getMessage());
            }
        }
        return violationMap;
    }
}
