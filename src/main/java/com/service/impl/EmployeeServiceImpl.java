package com.service.impl;


import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.EmployeeDAOImpl;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.utils.ConstraintViolationsParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private static EmployeeServiceImpl instance;
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    private EmployeeServiceImpl(){}
    public static EmployeeServiceImpl getInstance(){
        if (instance == null){
            instance = new EmployeeServiceImpl();
        }
        return instance;
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) throws ServiceException, ValidationException {
        Map<String,String> violationMap = ConstraintViolationsParser.getViolationsMap(employee);
        if (violationMap.size() > 0){
            throw new ValidationException("Validations problems with employee bean", violationMap);
        }
        try {
             employeeDAO.saveOrUpdate(employee);
        } catch (DAOException e) {
            throw new ServiceException("Fail to create or update employee at service layer", e);
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws ServiceException {
        Employee employee;
        try {
            employee = employeeDAO.getEmployee(employeeId);
        } catch (DAOException e) {
            throw new ServiceException("Fail to get employee at service layer", e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() throws ServiceException{
        List<Employee> employeeList;
        try {
            employeeList = employeeDAO.getAllEmployee();
        } catch (DAOException e) {
            throw new ServiceException("Fail to getAll employee at service layer", e);
        }
        return employeeList;
    }

    @Override
    public List<Employee> getDepartmentsEmployees(Integer departmentId) throws ServiceException{
        List<Employee> employeeList;
        try {
            employeeList = employeeDAO.getEmployeeByDepartment(departmentId);
        } catch (DAOException e) {
            throw new ServiceException("Fail to get department employees at service layer", e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws ServiceException{
        try {
            employeeDAO.deleteEmployee(employeeId);
        } catch (DAOException e) {
            throw new ServiceException("Fail to delete employee at service layer", e);
        }
    }


}
