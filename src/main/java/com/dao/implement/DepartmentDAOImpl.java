package com.dao.implement;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.exception.DAOException;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public void saveOrUpdate(Department department) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SAVE_OR_UPDATE_DEPARTMENT)) {
            statement.setString(1, department.getDepartmentName());
            if (department.getDepartmentId() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, department.getDepartmentId());
            }
            statement.execute();
        }catch (SQLException e){
            throw new DAOException("Fail to save/update department",e);
        }
    }

    @Override
    public Department getDepartment(Integer departmentId) throws DAOException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                department =buildDepartment(resultSet);
            }
        }catch (SQLException e){
            throw new DAOException("Fail to get department",e);
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_ALL_DEPARTMENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departmentList.add(buildDepartment(resultSet));
            }
        }catch (SQLException e){
            throw new DAOException("Fail to get all departments",e);
        }
        return departmentList;
    }

    @Override
    public void deleteDepartment(Integer departmentId) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_DEPARTMENT)) {
            statement.setInt(1, departmentId);
            statement.execute();
        }catch (SQLException e){
            throw new DAOException("Fail to delete department",e);
        }
    }

    @Override
    public boolean checkUnique(String departmentName, Integer departmentId) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.CHECK_DEP_NAME_UNIQUE_WITH_ID)) {
            statement.setString(1, departmentName);
            if (departmentId == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, departmentId);
            }
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        }catch (SQLException e){
            throw new DAOException("Fail to check unique department",e);
        }
    }

    private Department buildDepartment(ResultSet resultSet) throws SQLException{
        return new Department(resultSet.getInt("department_id"),
                resultSet.getString("department_name"));
    }
}
