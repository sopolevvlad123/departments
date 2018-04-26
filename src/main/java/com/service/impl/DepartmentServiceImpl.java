package com.service.impl;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.DepartmentDAOImpl;
import com.exception.DAOException;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.utils.ConstraintViolationsParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl implements DepartmentService {
    private static DepartmentServiceImpl instance;
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    private DepartmentServiceImpl(){}
    public static DepartmentServiceImpl getInstance(){
        if (instance == null){
            instance = new DepartmentServiceImpl();
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(Department department) throws DAOException, ValidationException {
        Map<String,String> violationMap = ConstraintViolationsParser.getViolationsMap(department);
        if (violationMap.size() > 0){
            throw new ValidationException("Fail to create or update department", violationMap);
        }
        try {
            departmentDAO.saveOrUpdate(department);
        } catch (SQLException e) {
            throw new DAOException("Fail to create or update department", e);
        }

    }

    @Override
    public Department getDepartment(Integer departmentId) throws DAOException{
        Department department;
        try {
            department = departmentDAO.getDepartment(departmentId);
        } catch (SQLException e) {
            throw new DAOException("Fail to get department", e);
        }
        return department;
    }

    public List<Department> getAllDepartments() throws DAOException{
        List<Department> departmentList;
        try {
            departmentList = departmentDAO.getAllDepartments();
        } catch (SQLException e) {
            throw new DAOException("Fail to get all departments", e);
        }
        return departmentList;
    }

    @Override
    public void deleteDepartment(Integer departmentId) throws DAOException {
        try {
            departmentDAO.deleteDepartment(departmentId);
        } catch (SQLException e) {
            throw new DAOException("Fail to delete department", e);
        }

    }

}
