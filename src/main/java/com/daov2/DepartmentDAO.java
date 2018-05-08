package com.daov2;

import com.bean.Department;
import com.exception.DAOException;

import java.util.List;

public interface DepartmentDAO extends DAO {

    Department getDepartmentByID(Integer departmentId) throws DAOException;

    List<Department> getAllDepartments() throws DAOException;

    Department getDepartmentByName(String departmentName)throws DAOException;


}
