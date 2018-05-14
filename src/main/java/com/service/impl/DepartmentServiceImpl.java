package com.service.impl;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.utils.ConstraintViolationsParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final static Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDAO hiberDepartmentDao;

    @Autowired
    private ConstraintViolationsParser constraintViolationsParser;

    @Override
    public void saveOrUpdate(Department department) throws ServiceException, ValidationException {
        Map<String, String> violationMap = constraintViolationsParser.getViolationsMap(department);
        if (violationMap.size() > 0) {
            throw new ValidationException("Validations problems with department bean", violationMap);
        }
        try {
            hiberDepartmentDao.saveOrUpdate(department);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to create or update department at service layer", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartment(Integer departmentId) throws ServiceException {
        Department department;
        try {
            department = hiberDepartmentDao.getDepartmentByID(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get department at service layer", e);
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() throws ServiceException {
        List<Department> departmentList;
        try {
            departmentList = hiberDepartmentDao.getAllDepartments();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get all departments at service layer", e);
        }
        return departmentList;
    }

    @Override
    public void deleteDepartment(Integer departmentId) throws ServiceException{
        try {
            hiberDepartmentDao.delete(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to delete department at service layer", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentByName(String name) throws ServiceException {
        Department department;
        try {
            department = hiberDepartmentDao.getDepartmentByName(name);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get department by name at service layer", e);
        }
        return department;
    }

}
