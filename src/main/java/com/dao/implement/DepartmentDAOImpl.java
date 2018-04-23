package com.dao.implement;

import com.bean.Department;
import com.bean.Employee;
import com.dao.DepartmentDAO;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public boolean createDepartment(String name) throws SQLException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.INSERT_DEPARTMENT)) {
            statement.setString(1, name);
            statement.execute();
            result = true;
        }
        return result;
    }

    public Department getDepartment(String departmentName) throws SQLException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement1 = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_NAME);
                 PreparedStatement statement2 = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENTS_EMPLOYEE_BY_NAME)) {
                statement1.setString(1, departmentName);
                statement2.setString(1, departmentName);
                try (ResultSet resultSet = statement1.executeQuery()) {
                    while (resultSet.next()) {
                        department = new Department(resultSet.getInt("department_id"),
                                resultSet.getString("department_name"));
                    }
                }
                try (ResultSet resultSet1 = statement2.executeQuery()) {
                    while (resultSet1.next()) {
                        Employee employee = new Employee(resultSet1.getInt("employee_id"),
                                resultSet1.getString("first_name"),
                                resultSet1.getString("second_name"),
                                resultSet1.getString("email"),
                                resultSet1.getInt("salary"),
                                resultSet1.getDate("hire_date"),
                                resultSet1.getInt("department_id"),
                                resultSet1.getString("department_name"));
                        department.addEmployee(employee);
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
        return department;
    }

    /*@Override
    public Department getDepartment(Integer departmentId) throws SQLException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement1 = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_ID);
                 PreparedStatement statement2 = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENTS_EMPLOYEE_BY_ID)) {
                statement1.setInt(1, departmentId);
                statement2.setInt(1, departmentId);
                try (ResultSet resultSet = statement1.executeQuery()) {
                    while (resultSet.next()) {
                        department = new Department(resultSet.getInt("department_id"),
                                resultSet.getString("department_name"));
                    }
                }
                try (ResultSet resultSet1 = statement2.executeQuery()) {
                    while (resultSet1.next()) {
                        Employee employee = new Employee(resultSet1.getInt("employee_id"),
                                resultSet1.getString("first_name"),
                                resultSet1.getString("second_name"),
                                resultSet1.getString("email"),
                                resultSet1.getInt("salary"),
                                resultSet1.getDate("hire_date"),
                                resultSet1.getInt("department_id"),
                                resultSet1.getString("department_name"));
                        department.addEmployee(employee);
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
        return department;
    }*/

    @Override
    public Department getDepartment(Integer departmentId) throws SQLException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                department = new Department(resultSet.getInt("department_id"),resultSet.getString("department_name"));
            }
        }

        return department;
    }

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_ALL_DEPARTMENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departmentList.add(new Department(resultSet.getInt("department_id"),
                        resultSet.getString("department_name")));
            }
        }
        return departmentList;
    }

    @Override
    public boolean updateDepartment(Department department) throws SQLException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.UPDATE_DEPARTMENT)) {
            statement.setString(1, department.getDepartmentName());
            statement.setInt(2, department.getDepartmentId());
            statement.execute();
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteDepartment(Integer departmentId) throws SQLException {
        boolean result = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_DEPARTMENT)) {
            statement.setInt(1, departmentId);
            statement.execute();
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkUnique(String departmentName, Integer departmentId) throws SQLException {
        if (departmentId == null){
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_DEP_NAME_UNIQUE_NO_ID)) {
                statement.setString(1, departmentName);
                ResultSet resultSet = statement.executeQuery();
                return !resultSet.next();
            }
        }else {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_DEP_NAME_UNIQUE_WITH_ID)) {
                statement.setString(1, departmentName);
                statement.setInt(2,departmentId);
                ResultSet resultSet = statement.executeQuery();
                return !resultSet.next();
            }
        }

    }


}
