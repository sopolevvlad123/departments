package com.validator;

import com.bean.Department;
import com.exception.ServiceException;
import com.service.DepartmentService;
import com.service.impl.DepartmentServiceImpl;
import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class DepartmentNameValidator implements CheckWithCheck.SimpleCheck{
    final static Logger logger = Logger.getLogger(DepartmentNameValidator.class);

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Override
    public boolean isSatisfied(Object validateObj, Object value) {
        boolean result = false;
        Department validatedDepartment = (Department) validateObj;
        try {
           Department dbDepartment = departmentServiceImpl.getDepartmentByName(validatedDepartment.getDepartmentName());
            result = dbDepartment == null ? true : dbDepartment.getDepartmentId().equals(validatedDepartment.getDepartmentId());
        } catch (ServiceException e) {
            logger.error(e);
        }
        return result;
    }


}
