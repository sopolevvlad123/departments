package com.validator;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.HiberImpl.HiberEmployeeDAOImpl;
import com.dao.implement.JDBCImpl.EmployeeDAOImpl;
import com.exception.DAOException;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public  class EmployeeEmailValidator implements CheckWithCheck.SimpleCheck{
    final static Logger logger = Logger.getLogger(EmployeeEmailValidator.class);

    private EmployeeDAO employeeDAO = new HiberEmployeeDAOImpl();

    @Override
    public boolean isSatisfied(Object valObj, Object value) {
        Employee employee = (Employee) valObj;
        boolean result = false;
        try {
            result =   employeeDAO.checkUnique(employee.getEmail(),employee.getEmployeeId());
        } catch (DAOException e) {
            logger.error(e);
        }
        return result;
    }
}