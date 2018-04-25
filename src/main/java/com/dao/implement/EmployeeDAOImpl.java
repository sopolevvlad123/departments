package com.dao.implement;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void saveOrUpdate(Employee employee) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SAVE_OR_UPDATE_EMPLOYEE)) {
            statement.setString(1, employee.getEmail());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setInt(4, employee.getSalary());
            statement.setDate(5, new java.sql.Date(employee.getHireDate().getTime()));
            statement.setInt(6, employee.getDepartmentId());
            if (employee.getEmployeeId() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, employee.getEmployeeId());
            }
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
            statement.setInt(1, departmentId);
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
    public void deleteEmployee(Integer employeeId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_EMPLOYEE)) {
            statement.setInt(1, employeeId);
            statement.execute();
        }
    }

    @Override
    public boolean checkUnique(String email, Integer employeeId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_IS_EMAIL_UNIQUE)) {
            statement.setString(1, email);
            if (employeeId == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, employeeId);
            }
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        }
    }

}

