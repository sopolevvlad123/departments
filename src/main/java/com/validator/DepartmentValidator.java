package com.validator;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.DepartmentDAOImpl;
import com.exception.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentValidator {
    private Validator validator = new Validator();
    private static Map<String,String> validationNameHelp = new HashMap<>();
    static {
        validationNameHelp.put("com.bean.Department.departmentName", "departmentNameViolation");

    }

    public void validateDepartment(Department department) throws ValidationException {
     Map <String,String> violationsMap = getConstraintViolationMap(department);
     if (violationsMap.size() > 0) throw new ValidationException("Department data is not valid",violationsMap);
    }

    private Map<String,String> getConstraintViolationMap(Department department) {
        Map<String, String> violationMap = new HashMap<>();
        List<ConstraintViolation> violations = validator.validate(department);
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
