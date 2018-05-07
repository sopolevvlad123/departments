package com.daov2;

import com.bean.Employee;
import com.exception.DAOException;

import java.util.List;

public interface EmpDAO extends DAO {
    Employee getEmployeeByID(Integer employeeId) throws DAOException;
    List<Employee> getEmployeesByDepartmentID(Integer departmentId) throws DAOException;
    Employee getEmployeeByEmail(String email) throws DAOException;

}
