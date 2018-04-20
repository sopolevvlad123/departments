package com.service;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.DepartmentDAOImpl;
import com.exception.DBException;
import net.sf.oval.Validator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentService {

    private static DepartmentService instance;

    private DepartmentService(){}

    public static DepartmentService getInstance(){
      if (instance == null){
          instance = new DepartmentService();
      }
      return instance;
    }

    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    private Validator validator = new Validator();

    public boolean createDepartment(String name) {
        try {
            return departmentDAO.createDepartment(name);
        } catch (SQLException e) {
            throw new DBException("Fail to create department", e);
        }
    }

    public Department getDepartment(int departmentId) {
        Department department = null;
        try {
            department = departmentDAO.getDepartment(departmentId);
        } catch (SQLException e) {
            throw new DBException("Fail to get department", e);
        }
        return department;
    }

    public List<Department> getAllDepartments() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAllDepartments();
        } catch (SQLException e) {
            throw new DBException("Fail to get all departments", e);
        }

        return departmentList;
    }

    public boolean updateDepartment(Department department) {
        try {
            return departmentDAO.updateDepartment(department);
        } catch (SQLException e) {
            throw new DBException("Fail to update department", e);
        }
    }

    public boolean deleteDepartment(int departmentId) {
        try {
            return departmentDAO.deleteDepartment(departmentId);
        } catch (SQLException e) {
            throw new DBException("Fail to delete department", e);
        }
    }

    public boolean checkUnique(String departmentName) {

        try {
            return departmentDAO.checkUnique(departmentName);
        } catch (SQLException e) {
            throw new DBException("Fail to check unique department", e);
        }
    }

    public Map<String,String> validationMap(Department department) {
        Map<String, String>  resut = new HashMap<>();
        if (validator.validate(department).size()> 0){

        }
        return resut;
    }
}
