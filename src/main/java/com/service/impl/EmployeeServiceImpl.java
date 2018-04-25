package com.service.impl;


import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.EmployeeDAOImpl;
import com.exception.DAOException;
import com.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

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
    public void saveOrUpdateEmployee(Employee employee) throws DAOException {
        try {
             employeeDAO.saveOrUpdate(employee);
        } catch (SQLException e) {
            throw new DAOException("Fail to create employee", e);
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws DAOException {
        Employee employee;
        try {
            employee = employeeDAO.getEmployee(employeeId);
        } catch (SQLException e) {
            throw new DAOException("Fail to get employee", e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() throws DAOException{
        List<Employee> employeeList;
        try {
            employeeList = employeeDAO.getAllEmployee();
        } catch (SQLException e) {
            throw new DAOException("Fail to getAll employee", e);
        }
        return employeeList;
    }

    @Override
    public List<Employee> getDepartmentsEmployees(Integer departmentId) throws DAOException{
        List<Employee> employeeList;
        try {
            employeeList = employeeDAO.getEmployeeByDepartment(departmentId);
        } catch (SQLException e) {
            throw new DAOException("Fail to get department employees", e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws DAOException{
        try {
            employeeDAO.deleteEmployee(employeeId);
        } catch (SQLException e) {
            throw new DAOException("Fail to delete employee", e);
        }
    }

}
