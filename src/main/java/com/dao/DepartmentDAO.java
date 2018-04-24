package com.dao;

import com.bean.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    void saveOrUpdate(Department department) throws SQLException;

    void createDepartment(String name) throws SQLException;

    Department getDepartment(Integer departmentId) throws SQLException;

    List<Department> getAllDepartments() throws SQLException;

    void deleteDepartment(Integer departmentId) throws SQLException;

    boolean checkUnique(String departmentName, Integer departmentId) throws SQLException;


}
