package com.service.impl;


import com.bean.Employee;
import com.daov2.EmployeeDAO;
import com.daov2.impl.hiber.HiberEmployeeDao;
import com.daov2.impl.jdbc.JDBCEmployeeDao;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.utils.ConstraintViolationsParser;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
    private static EmployeeServiceImpl instance;
    private EmployeeDAO employeeDAO = new HiberEmployeeDao();

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
            logger.error(e);
            throw new ServiceException("Fail to create or update employee at service layer", e);
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws ServiceException {
        Employee employee;
        try {
            employee = employeeDAO.getEmployeeByID(employeeId);
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
            employeeList = employeeDAO.getEmployeesByDepartmentID(departmentId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to get department employees at service layer", e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws ServiceException{
        try {
            employeeDAO.delete(employeeId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException("Fail to delete employee at service layer", e);
        }
    }


}
