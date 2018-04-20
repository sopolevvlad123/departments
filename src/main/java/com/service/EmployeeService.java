package com.service;


import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.implement.EmployeeDAOImpl;
import com.exception.DBException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EmployeeService {

    static final String DATE_FORMAT = "yyyy-mm-dd";
    private static EmployeeService instance;

    private EmployeeService(){}

    public static EmployeeService getInstance(){
        if (instance == null){
            instance = new EmployeeService();
        }
        return instance;
    }

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private Validator validator = new Validator();

    public boolean createEmployee(String email, String firstName, String lastName, int salary, Date hireDate, int departmentId) {
        try {
            return employeeDAO.createEmployee(email, firstName, lastName, salary, hireDate, departmentId);
        } catch (SQLException e) {
            throw new DBException("Fail to create employee", e);
        }
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        try {
            employee = employeeDAO.getEmployee(employeeId);
        } catch (SQLException e) {
            throw new DBException("Fail to get employee", e);
        }
        return employee;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeDAO.getAllEmployee();
        } catch (SQLException e) {
            throw new DBException("Fail to getAll employee", e);
        }
        return employeeList;
    }

    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeDAO.getEmployeeByDepartment(departmentId);
        } catch (SQLException e) {
            throw new DBException("Fail to get department employees", e);
        }
        return employeeList;
    }

    public boolean updateEmployee(Employee employee) {
        try {
            return employeeDAO.updateEmployee(employee);
        } catch (SQLException e) {
            throw new DBException("Fail to update employee", e);
        }
    }

    public boolean deleteEmployee(int employeeId) {
        try {
            return employeeDAO.deleteEmployee(employeeId);
        } catch (SQLException e) {
            throw new DBException("Fail to delete employee", e);
        }
    }

    public boolean checkUnique(String email) {
        try {
            return employeeDAO.checkUnique(email);
        } catch (SQLException e) {
            throw new DBException("Fail to check unique employee", e);
        }
    }

    /*public Map<String,List<String>> validationProblemsMap(String firstName, String lastName, String email, String salaryString, String hireDate, String departmentId, String employeeId){
        return validationProblemsMap(firstName, lastName, email, salaryString, hireDate, departmentId, e);
    };*/


        public Map<String,String> validationProblemsMap(String firstName, String lastName, String email, String salaryString, String hireDate, String departmentId, String employeeId){
        int salary = 0;
        java.sql.Date date = null;
        if (isDateValid(hireDate, DATE_FORMAT)) {
            date = java.sql.Date.valueOf(hireDate);
        }
        if (isNumValid(salaryString)) {
            salary = new BigInteger(salaryString).intValue();
        }
        Employee employee = new Employee(firstName, lastName, email, salary, date, Integer.parseInt(departmentId));

        System.out.println(employee);
        return validViolMap(employee);
    }

    private Map<String,String> validViolMap(Employee employee) {
        Map<String,String> violationMap = new HashMap<>();
        Map<String,String> violHelpMap = validationNameHelp();
        List<ConstraintViolation> violations = validator.validate(employee);
        System.out.println(violations);

        if (violations.size() > 0){
            int count = 0;
            for (ConstraintViolation violation : violations){
                System.out.println(++count);


                if (violHelpMap.containsKey(violation.getContext().toString())){
                violationMap.put(violHelpMap.get(violation.getContext().toString()),violation.getMessage());
                }
            }

        }
        return violationMap;
    }
    private Map<String,String> validationNameHelp(){
        Map<String,String> violHelpMap = new HashMap<>();
        violHelpMap.put("com.bean.Employee.firstName", "firstNameViolations");
        violHelpMap.put("com.bean.Employee.lastName", "lastNameViolations");
        violHelpMap.put("com.bean.Employee.email", "emailViolations");
        violHelpMap.put("com.bean.Employee.salary", "salaryViolations");
        violHelpMap.put("com.bean.Employee.hireDate", "hireDateViolations");
        return violHelpMap;
    }


    private boolean isNumValid(String num) {
        if (num == null) {
            return false;
        }
        return Pattern.matches("[0-9]+", num) && num.length() < 50;
    }

    private boolean isDateValid(String date, String dateFormat) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date datvalidDate = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


}
