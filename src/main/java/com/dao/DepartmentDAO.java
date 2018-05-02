package com.dao;

import com.bean.Department;
import com.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    void saveOrUpdate(Department department) throws DAOException;

    Department getDepartment(Integer departmentId) throws DAOException;

    List<Department> getAllDepartments() throws DAOException;

    void deleteDepartment(Integer departmentId) throws DAOException;

    boolean checkUnique(String departmentName, Integer departmentId) throws DAOException;


}
