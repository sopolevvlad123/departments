package com.service;

import com.bean.Employee;
import com.exception.DAOException;

import java.util.List;

public interface EmployeeService {
    void saveOrUpdateEmployee(Employee employee) throws DAOException;

    Employee getEmployee(Integer employeeId) throws DAOException;

    List<Employee> getAllEmployees() throws DAOException;

    List<Employee> getDepartmentsEmployees(Integer departmentId) throws DAOException;

    void deleteEmployee(Integer employeeId) throws DAOException;

}
