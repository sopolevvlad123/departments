package com.dao;

import com.bean.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    boolean createDepartment(String name) throws SQLException;

    Department getDepartment(Integer departmentId) throws SQLException;

    List<Department> getAllDepartments() throws SQLException;

    boolean updateDepartment(Department department) throws SQLException;

    boolean deleteDepartment(Integer departmentId) throws SQLException;

    boolean checkUnique(String departmentName) throws SQLException;


}
