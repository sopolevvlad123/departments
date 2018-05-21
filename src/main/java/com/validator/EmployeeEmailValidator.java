package com.validator;

import com.bean.Employee;
import com.exception.ServiceException;
import com.service.EmployeeService;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEmailValidator implements CheckWithCheck.SimpleCheck {
    private final static Logger logger = Logger.getLogger(EmployeeEmailValidator.class);
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isSatisfied(Object valObj, Object value) {
        Employee validatedEmployee = (Employee) valObj;
        boolean result = false;
        try {
            Employee dbEmployee = employeeService.getEmployeeByEmail(validatedEmployee.getEmail());
            result = dbEmployee == null || validatedEmployee.getEmployeeId().equals(dbEmployee.getEmployeeId());
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }
}