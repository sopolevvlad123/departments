package com.service;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.dao.implement.DepartmentDAOImpl;
import com.exception.DBException;
import net.sf.oval.ConstraintViolation;
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
        Department department;
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

    public boolean checkUnique(String departmentName,Integer departmentId) {
        try {
            return departmentDAO.checkUnique(departmentName,departmentId);
        } catch (SQLException e) {
            throw new DBException("Fail to check unique department", e);
        }
    }

    public Map<String,String> validationProblemsMap(String departmentName){
        return validationProblemsMap(departmentName, null);
    }

    public Map<String,String> validationProblemsMap(String departmentName,String departmentId){
        Department department = null;
        if (departmentId == null){
            department = new Department(departmentName);
        }else {
            department = new Department(Integer.parseInt(departmentId),departmentName);
        }
        return validViolMap(department);
    }

    private Map<String,String> validViolMap(Department department) {
        Map<String, String> violationMap = new HashMap<>();
        Map<String, String> violHelpMap = validationNameHelp();
        List<ConstraintViolation> violations = validator.validate(department);
        if (violations.size() > 0) {
            for (int i = violations.size() - 1; i >= 0; i--) {
                if (violHelpMap.containsKey(violations.get(i).getContext().toString())) {
                    violationMap.put(violHelpMap.get(violations.get(i).getContext().toString()), violations.get(i).getMessage());
                }
            }
        }
        return violationMap;
    }

    private Map<String,String> validationNameHelp(){
        Map<String,String> violHelpMap = new HashMap<>();
        violHelpMap.put("com.bean.Department.departmentName", "departmentNameViolation");
        return violHelpMap;
    }
}
