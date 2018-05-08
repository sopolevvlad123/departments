package com.daov2.impl.jdbc;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.JDBCImpl.EmployeeDAOImpl;
import com.daov2.EmpDAO;
import com.exception.DAOException;
import com.utils.ConnectionFactory;
import com.utils.SQLConstants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class JDBCEmployeeDao implements EmpDAO {
    final static Logger logger = Logger.getLogger(JDBCEmployeeDao.class);
    private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        Employee employee = (Employee) object;
        employeeDAO.saveOrUpdate(employee);
    }

    @Override
    public void delete(Integer id) throws DAOException {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public Employee getEmployeeByID(Integer employeeId) throws DAOException {
        return employeeDAO.getEmployee(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentID(Integer departmentId) throws DAOException {
        return employeeDAO.getEmployeeByDepartment(departmentId);
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws DAOException {
        Employee employee = null;
       /* try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_EMPLOYEE_BY_EMAIL)) {
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = buildEmployee(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Fail to get employee by email", e);
        }*/
        return employee;
    }

}
