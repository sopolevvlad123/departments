package com.validator;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.HiberImpl.HiberEmployeeDAOImpl;
import com.dao.implement.JDBCImpl.EmployeeDAOImpl;
import com.exception.DAOException;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class EmployeeEmailValidator implements CheckWithCheck.SimpleCheck {
    final static Logger logger = Logger.getLogger(EmployeeEmailValidator.class);

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean isSatisfied(Object valObj, Object value) {
        Employee validatedEmployee = (Employee) valObj;
        boolean result = false;
        try {
            Employee dbEmployee = employeeDAO.getEmployeeByEmail(validatedEmployee.getEmail());
            result = validatedEmployee.getEmployeeId() == null ? dbEmployee == null : validatedEmployee.getEmployeeId() == dbEmployee.getEmployeeId();
        } catch (DAOException e) {
            logger.error(e);
        }
        return result;
    }
}