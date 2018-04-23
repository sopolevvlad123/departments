package com.dao;

import com.bean.Employee;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface EmployeeDAO {
    boolean createEmployee(String email, String firstName, String lastName, int salary, Date hireDate, int departmentId) throws SQLException;

    Employee getEmployee(Integer employeeId) throws SQLException;

    List<Employee> getAllEmployee() throws SQLException;

    List<Employee> getEmployeeByDepartment(Integer departmentId) throws SQLException;

    boolean updateEmployee(Employee employee) throws SQLException;

    boolean deleteEmployee(Integer employeeId) throws SQLException;

    boolean checkUnique(String email, Integer employeeId) throws SQLException;
}
