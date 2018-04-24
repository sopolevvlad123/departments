package com.service;

import com.bean.Department;

import java.util.List;

public interface DepartmentService {


    void saveOrUpdate(Department department);
    Department getDepartment(Integer departmentId);
    List<Department> getAllDepartments();
    void deleteDepartment(Integer departmentId);
}
