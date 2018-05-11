package com.dao.impl.jdbc;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.exception.DAOException;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class JDBCDepartmentDao implements DepartmentDAO {
    final static Logger logger = Logger.getLogger(JDBCDepartmentDao.class);

    @Override
    public Department getDepartmentByID(Integer departmentId) throws DAOException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                department = buildDepartment(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get department by ID " + departmentId + " by JDBC", e);
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
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get all departments by JDBC", e);
        }
        return departmentList;
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws DAOException {
        Department department = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_DEPARTMENT_BY_NAME)) {
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                department = buildDepartment(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get department by name " + departmentName + " by JDBC", e);
        }
        return department;
    }

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        Department department = (Department) object;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SAVE_OR_UPDATE_DEPARTMENT)) {
            statement.setString(1, department.getDepartmentName());
            if (department.getDepartmentId() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, department.getDepartmentId());
            }
            statement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to save/update department " + department + " by JDBC", e);
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_DEPARTMENT)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to delete department " + id + " by JDBC", e);
        }
    }

    private Department buildDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }
}

