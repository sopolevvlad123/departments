package com.service;

import com.bean.Department;
import com.exception.ServiceException;
import com.exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    void saveOrUpdate(Department department) throws ServiceException, ValidationException;

    Department getDepartment(Integer departmentId) throws ServiceException;

    List getAllDepartments() throws ServiceException;

    void deleteDepartment(Integer departmentId) throws ServiceException;

    Department getDepartmentByName(String name) throws ServiceException;
}
