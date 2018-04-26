package com.service;

import com.bean.Department;
import com.exception.DAOException;
import com.exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    void saveOrUpdate(Department department) throws DAOException, ValidationException;

    Department getDepartment(Integer departmentId) throws DAOException;

    List<Department> getAllDepartments() throws DAOException;

    void deleteDepartment(Integer departmentId) throws DAOException;
}
