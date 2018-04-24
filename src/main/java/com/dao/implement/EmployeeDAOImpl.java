package com.dao.implement;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void saveOrUpdate(Employee employee) throws SQLException {

    }

    @Override
    public void createEmployee(String email, String firstName, String lastName, int salary, java.util.Date hireDate, int departmentId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.INSERT_EMPLOYEE)) {
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setInt(4, salary);
            statement.setDate(5, new java.sql.Date(hireDate.getTime()));
            statement.setInt(6, departmentId);
            statement.execute();
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws SQLException {
        Employee employee = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_EMPLOYEE_BY_ID)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("salary"),
                        resultSet.getDate("hire_date"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"));
            }

        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_ALL_EMPLOYEE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("salary"),
                        resultSet.getDate("hire_date"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"));
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    @Override
    public List<Employee> getEmployeeByDepartment(Integer departmentId) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENTS_EMPLOYEE_BY_ID)) {
            statement.setInt(1,departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("salary"),
                        resultSet.getDate("hire_date"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"));
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.UPDATE_EMPLOYEE)) {
            statement.setString(1, employee.getEmail());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setInt(4, employee.getSalary());
            statement.setDate(5, new java.sql.Date(employee.getHireDate().getTime()));
            statement.setInt(6, employee.getDepartmentId());
            statement.setInt(7, employee.getEmployeeId());
            statement.execute();
        }
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_EMPLOYEE)) {
            statement.setInt(1, employeeId);
            statement.execute();
        }
    }

    @Override
    public boolean checkUnique(String email, Integer employeeId) throws SQLException {
        if (employeeId == null){
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_IS_EMAIL_UNIQUE_NO_ID)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                return ! resultSet.next();
            }
        }else {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_IS_EMAIL_UNIQUE_WITH_ID)) {
                statement.setString(1, email);
                statement.setInt(2,employeeId);
                ResultSet resultSet = statement.executeQuery();
                return ! resultSet.next();
            }
        }
    }
}

