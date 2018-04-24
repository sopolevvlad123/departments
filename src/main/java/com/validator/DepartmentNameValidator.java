package com.validator;


import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.DepartmentDAOImpl;
import net.sf.oval.constraint.CheckWithCheck;

import java.sql.SQLException;

public  class DepartmentNameValidator implements CheckWithCheck.SimpleCheck{

    DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    @Override
    public boolean isSatisfied(Object validateObj, Object value) {
        boolean result = false;
        Department department = (Department) validateObj;
        try {
            result = departmentDAO.checkUnique(department.getDepartmentName(),department.getDepartmentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
