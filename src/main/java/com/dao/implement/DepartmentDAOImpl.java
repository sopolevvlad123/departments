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
