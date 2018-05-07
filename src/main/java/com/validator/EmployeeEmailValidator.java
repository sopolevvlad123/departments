package com.validator;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.JDBCImpl.EmployeeDAOImpl;
import net.sf.oval.constraint.CheckWithCheck;

import java.sql.SQLException;

public  class EmployeeEmailValidator implements CheckWithCheck.SimpleCheck{
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean isSatisfied(Object valObj, Object value) {
        Employee employee = (Employee) valObj;
        boolean result = false;
        try {
            result =   employeeDAO.checkUnique(employee.getEmail(),employee.getEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}