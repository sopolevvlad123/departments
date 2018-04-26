package com.service;

import com.bean.Employee;
import com.exception.DAOException;
import com.exception.ValidationException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void saveOrUpdateEmployee(Employee employee) throws DAOException, ValidationException;

    Employee getEmployee(Integer employeeId) throws DAOException;

    List<Employee> getAllEmployees() throws DAOException;

    List<Employee> getDepartmentsEmployees(Integer departmentId) throws DAOException;

    void deleteEmployee(Integer employeeId) throws DAOException;


}
