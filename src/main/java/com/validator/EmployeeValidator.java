package com.validator;

import com.bean.Department;
import com.bean.Employee;
import com.exception.ValidationException;
import com.service.impl.EmployeeServiceImpl;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeValidator {
    private Validator validator = new Validator();
    private static Map<String,String> validationNameHelp = new HashMap<>();
    static {
        validationNameHelp.put("com.bean.Employee.firstName", "firstNameViolations");
        validationNameHelp.put("com.bean.Employee.lastName", "lastNameViolations");
        validationNameHelp.put("com.bean.Employee.email", "emailViolations");
        validationNameHelp.put("com.bean.Employee.salary", "salaryViolations");
        validationNameHelp.put("com.bean.Employee.hireDate", "hireDateViolations");

    }

    public void validateEmployee(Employee employee) throws ValidationException {
        Map <String,String> violationsMap = getConstraintViolationMap(employee);
        if (violationsMap.size() > 0) throw new ValidationException("Employee data is not valid",violationsMap);
    }

    private Map<String,String> getConstraintViolationMap(Employee employee) {
        Map<String, String> violationMap = new HashMap<>();
        List<ConstraintViolation> violations = validator.validate(employee);
        if (violations.size() > 0) {
            for (int i = violations.size() - 1; i >= 0; i--) {
                if (validationNameHelp.containsKey(violations.get(i).getContext().toString())) {
                    violationMap.put(validationNameHelp.get(violations.get(i).getContext().toString()), violations.get(i).getMessage());
                }
            }
        }
        return violationMap;
    }
}
