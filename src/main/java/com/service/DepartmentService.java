package com.service;

import com.bean.Department;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    void saveOrUpdate(Department department) throws ServiceException, ValidationException;

    Department getDepartment(Integer departmentId) throws ServiceException;

    List<Department> getAllDepartments() throws ServiceException;

    void deleteDepartment(Integer departmentId) throws ServiceException;
}
