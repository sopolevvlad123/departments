package com.utils;

import com.validator.Validator;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConstraintViolationsParser {
    public Map<String, String> getViolationsMap(Object object) {
        Map<String, String> violationMap = new HashMap<>();
        List<ConstraintViolation> violations = Validator.getViolationsList(object);
        if (violations.size() > 0) {
            FieldContext fieldContext;
            for (int i = violations.size() - 1; i >= 0; i--) {
                fieldContext = (FieldContext) violations.get(i).getCheckDeclaringContext();
                violationMap.put(fieldContext.getField().getName(), violations.get(i).getMessage());
            }
        }
        return violationMap;
    }
}
