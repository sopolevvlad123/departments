package com.dao;

import com.bean.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void saveOrUpdate(Employee employee) throws SQLException;

    Employee getEmployee(Integer employeeId) throws SQLException;

    List<Employee> getAllEmployee() throws SQLException;

    List<Employee> getEmployeeByDepartment(Integer departmentId) throws SQLException;

    void deleteEmployee(Integer employeeId) throws SQLException;

    boolean checkUnique(String email, Integer employeeId) throws SQLException;

}
