package com.dao.impl.jdbc;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.exception.DAOException;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEmployeeDao implements EmployeeDAO {
    final static Logger logger = Logger.getLogger(JDBCEmployeeDao.class);

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        Employee employee = (Employee) object;
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
            logger.error(e);
            throw new DAOException("Fail to save/update employee " + employee + " by JDBC", e);
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_EMPLOYEE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to delete employee " + id + "by JDBC", e);
        }
    }

    @Override
    public Employee getEmployeeByID(Integer employeeId) throws DAOException {
        Employee employee = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_EMPLOYEE_BY_ID)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = buildEmployee(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get employee by ID " + employeeId + "by JDBC", e);
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentID(Integer departmentId) throws DAOException {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENTS_EMPLOYEE_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(buildEmployee(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get employees of the department # " + departmentId + "by JDBC", e);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws DAOException {
        Employee employee = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_EMPLOYEE_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = buildEmployee(resultSet);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get employee by email " + email + "by JDBC", e);
        }
        return employee;
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
