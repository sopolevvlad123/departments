package com.dao;

import com.bean.Employee;
import com.exception.DAOException;

import java.util.List;

public interface EmployeeDAO extends DAO {
    Employee getEmployeeByID(Integer employeeId) throws DAOException;

    List getEmployeesByDepartmentID(Integer departmentId) throws DAOException;

    Employee getEmployeeByEmail(String email) throws DAOException;

}
