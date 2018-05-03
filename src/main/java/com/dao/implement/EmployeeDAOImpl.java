package com.dao.implement;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.exception.DAOException;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void saveOrUpdate(Employee employee) throws DAOException {
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
        } catch (SQLException e) {
            throw new DAOException("Fail to save/update employee", e);
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws DAOException {
        Employee employee = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_EMPLOYEE_BY_ID)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = buildEmployee(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail to get employee", e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() throws DAOException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_ALL_EMPLOYEE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(buildEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail to get all employee", e);
        }
        return employeeList;
    }

    @Override
    public List<Employee> getEmployeeByDepartment(Integer departmentId) throws DAOException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENTS_EMPLOYEE_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(buildEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Fail to get departments employee employee", e);
        }
        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_EMPLOYEE)) {
            statement.setInt(1, employeeId);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Fail to delete employee", e);
        }
    }

    @Override
    public boolean checkUnique(String email, Integer employeeId) throws DAOException {
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
        } catch (SQLException e) {
            throw new DAOException("Fail to check unique employee", e);
        }
    }

    private Employee buildEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getInt("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("second_name"));
        employee.setSalary(resultSet.getInt("salary"));
        employee.setEmail(resultSet.getString("email"));
        employee.setHireDate(resultSet.getDate("hire_date"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        return employee;
    }

}

