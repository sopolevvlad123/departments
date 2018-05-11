package com.service.impl;


import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.utils.ConstraintViolationsParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeDAO hiberEmployeeDao;

    @Autowired
    private ConstraintViolationsParser constraintViolationsParser;

    @Override
    public void saveOrUpdateEmployee(Employee employee) throws ServiceException, ValidationException {
        Map<String,String> violationMap = constraintViolationsParser.getViolationsMap(employee);
        if (violationMap.size() > 0){
            throw new ValidationException("Validations problems with employee bean", violationMap);
        }
        try {
            hiberEmployeeDao.saveOrUpdate(employee);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to create or update employee at service layer", e);
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws ServiceException {
        Employee employee;
        try {
            employee = hiberEmployeeDao.getEmployeeByID(employeeId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get employee at service layer", e);
        }
        return employee;
    }



    @Override
    public List<Employee> getDepartmentsEmployees(Integer departmentId) throws ServiceException{
        List<Employee> employeeList;
        try {
            employeeList = hiberEmployeeDao.getEmployeesByDepartmentID(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get department employees at service layer", e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws ServiceException{
        try {
            hiberEmployeeDao.delete(employeeId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to delete employee at service layer", e);
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws ServiceException {
        Employee employee;
        try {
            employee = hiberEmployeeDao.getEmployeeByEmail(email);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get employee by email at service layer", e);
        }
        return employee;
    }


}
