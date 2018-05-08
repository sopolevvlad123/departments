package com.service.impl;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.HiberImpl.HiberDepartmentDAOImpl;
import com.dao.implement.JDBCImpl.DepartmentDAOImpl;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.utils.ConstraintViolationsParser;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl implements DepartmentService {
    final static Logger logger = Logger.getLogger(DepartmentServiceImpl.class);
    private static DepartmentServiceImpl instance;
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    private DepartmentServiceImpl(){}
    public static DepartmentServiceImpl getInstance(){
        if (instance == null){
            instance = new DepartmentServiceImpl();
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(Department department) throws ServiceException, ValidationException {
        Map<String,String> violationMap = ConstraintViolationsParser.getViolationsMap(department);
        if (violationMap.size() > 0){
            throw new ValidationException("Validations problems with department bean", violationMap);
        }
        try {
            departmentDAO.saveOrUpdate(department);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to create or update department at service layer", e);
        }

    }

    @Override
    public Department getDepartment(Integer departmentId) throws ServiceException{
        Department department;
        try {
            department = departmentDAO.getDepartment(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get department at service layer", e);
        }
        return department;
    }

    public List<Department> getAllDepartments() throws ServiceException{
        List<Department> departmentList;
        try {
            departmentList = departmentDAO.getAllDepartments();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get all departments at service layer", e);
        }
        return departmentList;
    }

    @Override
    public void deleteDepartment(Integer departmentId) throws ServiceException {
        try {
            departmentDAO.deleteDepartment(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to delete department at service layer", e);
        }

    }

}
