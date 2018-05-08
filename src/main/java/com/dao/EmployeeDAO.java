package com.dao;

import com.bean.Employee;
import com.exception.DAOException;

import java.util.List;

public interface EmployeeDAO {
    void saveOrUpdate(Employee employee) throws DAOException;

    Employee getEmployee(Integer employeeId) throws DAOException;

    List<Employee> getAllEmployee() throws DAOException;

    List<Employee> getEmployeeByDepartment(Integer departmentId) throws DAOException;

    void deleteEmployee(Integer employeeId) throws DAOException;

    Employee getEmployeeByEmail(String email) throws DAOException;

}
