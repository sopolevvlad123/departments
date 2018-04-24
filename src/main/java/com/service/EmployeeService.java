package com.service;

import com.bean.Employee;

import java.util.List;

public interface EmployeeService {
    void saveOrUpdateEmployee(Employee employee);
    Employee getEmployee(Integer employeeId);
    List<Employee> getAllEmployees();
    List<Employee> getDepartmentsEmployees(Integer departmentId);
    void deleteEmployee(Integer employeeId);

}
