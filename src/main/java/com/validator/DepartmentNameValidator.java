package com.validator;


import com.bean.Department;
import com.daov2.DepartmentDAO;
import com.daov2.impl.hiber.HiberDepartmentDao;
import com.daov2.impl.jdbc.JDBCDepartmentDao;
import com.exception.DAOException;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;

public  class DepartmentNameValidator implements CheckWithCheck.SimpleCheck{
    final static Logger logger = Logger.getLogger(DepartmentNameValidator.class);

    DepartmentDAO departmentDAO = new HiberDepartmentDao();

    @Override
    public boolean isSatisfied(Object validateObj, Object value) {
        boolean result = false;
        Department validatedDepartment = (Department) validateObj;
        try {
           Department dbDepartment = departmentDAO.getDepartmentByName(validatedDepartment.getDepartmentName());
            result = dbDepartment == null ? true : dbDepartment.getDepartmentId().equals(validatedDepartment.getDepartmentId());
        } catch (DAOException e) {
            logger.error(e);
        }
        return result;
    }
}
