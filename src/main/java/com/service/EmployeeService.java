package com.service;

import com.bean.Employee;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void saveOrUpdateEmployee(Employee employee) throws ServiceException, ValidationException;

    Employee getEmployee(Integer employeeId) throws ServiceException;

    List<Employee> getAllEmployees() throws ServiceException;

    List<Employee> getDepartmentsEmployees(Integer departmentId) throws ServiceException;

    void deleteEmployee(Integer employeeId) throws ServiceException;


}
