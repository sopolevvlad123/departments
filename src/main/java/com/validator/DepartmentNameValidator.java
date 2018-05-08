package com.validator;


import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.HiberImpl.HiberDepartmentDAOImpl;
import com.dao.implement.JDBCImpl.DepartmentDAOImpl;
import com.exception.DAOException;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public  class DepartmentNameValidator implements CheckWithCheck.SimpleCheck{
    final static Logger logger = Logger.getLogger(DepartmentNameValidator.class);

    DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    @Override
    public boolean isSatisfied(Object validateObj, Object value) {
        boolean result = false;
        Department validatedDepartment = (Department) validateObj;
        try {
           Department dbDepartment = departmentDAO.getDepartmentByName(validatedDepartment.getDepartmentName());
            System.out.println("department first  --" + validatedDepartment.getDepartmentId() );
            System.out.println("department second  --" +dbDepartment);

            result = validatedDepartment.getDepartmentId() == null ? dbDepartment == null : validatedDepartment.getDepartmentId() == dbDepartment.getDepartmentId();
        } catch (DAOException e) {
            logger.error(e);
        }
        return result;
    }
}
