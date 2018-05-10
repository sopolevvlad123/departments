package com.validator;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.impl.hiber.HiberEmployeeDao;
import com.dao.impl.jdbc.JDBCEmployeeDao;
import com.exception.DAOException;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;

public class EmployeeEmailValidator implements CheckWithCheck.SimpleCheck {
    final static Logger logger = Logger.getLogger(EmployeeEmailValidator.class);
    private EmployeeDAO employeeDAO = new HiberEmployeeDao();

    @Override
    public boolean isSatisfied(Object valObj, Object value) {
        Employee validatedEmployee = (Employee) valObj;
        boolean result = false;
        try {
            Employee dbEmployee = employeeDAO.getEmployeeByEmail(validatedEmployee.getEmail());
            result = dbEmployee == null ? true : validatedEmployee.getEmployeeId().equals(dbEmployee.getEmployeeId());
        } catch (DAOException e) {
            logger.error(e);
        }
        return result;
    }
}