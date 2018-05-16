package com.controller;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.utils.DataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static com.utils.ServletHandlerConstants.*;

@Controller
public class EmployeeController {
    final static Logger logger = Logger.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = SAVE_EMPLOYEE, method = RequestMethod.POST)
    public String saveEmployee(@RequestParam(value = FIRST_NAME) String firstName,
                               @RequestParam(value = LAST_NAME)  String lastName,
                               @RequestParam(value = EMAIL) String email,
                               @RequestParam(value = SALARY) String salary,
                               @RequestParam(value = HIRE_DATE) String hireDate,
                               @RequestParam(value = DEPARTMENT_ID) String departmentId,
                               @RequestParam(value = EMPLOYEE_ID, required = false) String employeeId,
                               Model model) throws  AppException {
        try {
            employeeService.saveOrUpdateEmployee(buildEmployee(firstName,lastName,email,salary,hireDate,departmentId,employeeId));
            return GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + departmentId;
        } catch (ValidationException e) {
            sendFailInput(firstName, lastName, email, salary, hireDate, model);
            logger.error(e);
            model.addAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            return "saveEmployee";
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update employee at application layer", e);
        }
    }

    @RequestMapping(value = GET_DEP_EMPLOYEES, method = RequestMethod.GET)
    public String getDepEmployees(@RequestParam(value = DEPARTMENT_ID) String departmentId,Model model) throws ServletException, IOException, AppException {
        List<Employee> employeeList;
        try {
            employeeList = employeeService.getDepartmentsEmployees(Integer.parseInt(departmentId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to get departments employee at application layer", e);
        }
        model.addAttribute(EMPLOYEE_LIST, employeeList);
        model.addAttribute(DEPARTMENT_ID, departmentId);
        return "employeeList";
    }

    @RequestMapping(value = DELETE_EMPLOYEE, method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam(value = DEPARTMENT_ID) String departmentId,@RequestParam(value = EMPLOYEE_ID) String employeeId) throws IOException, AppException {
        try {
            employeeService.deleteEmployee(Integer.parseInt(employeeId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete or update employee at application layer", e);
        }
        return GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + departmentId;
    }

  /*  @RequestMapping(value = PREPARE_EMPLOYEE, method = RequestMethod.GET)
    public void prepareEmployee(@ModelAttribute Employee employee
                                Model model) throws ServletException, IOException, AppException {
        if (DataParser.isIDValid(employeeId)) {
            try {
                Employee employee = employeeService.getEmployee(Integer.parseInt(employeeId));
                request.setAttribute(FIRST_NAME, employee.getFirstName());
                request.setAttribute(LAST_NAME, employee.getLastName());
                request.setAttribute(EMAIL, employee.getEmail());
                request.setAttribute(SALARY, employee.getSalary());
                request.setAttribute(HIRE_DATE, employee.getHireDate());
                request.setAttribute(DEPARTMENT_ID, employee.getDepartmentId());
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get employee at application layer", e);
            }
        }
        request.getRequestDispatcher(SAVE_EMPLOYEE_PAGE).forward(request, response);
    }*/

    private Employee buildEmployee(String firstName, String lastName, String email,
                                   String salary, String hireDate, String departmentId, String employeeId) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setSalary(Integer.parseInt(salary));
        employee.setHireDate(Date.valueOf(hireDate));
        employee.setDepartmentId(Integer.parseInt(departmentId));
        if ((DataParser.isIDValid(employeeId))) {
            employee.setEmployeeId(Integer.parseInt(employeeId));
        }
        return employee;
    }

    private void sendFailInput(String firstName, String lastName, String email,
                               String salary, String hireDate, Model model) {
        model.addAttribute(FIRST_NAME, firstName);
        model.addAttribute(LAST_NAME, lastName);
        model.addAttribute(EMAIL, email);
        model.addAttribute(SALARY, salary);
        model.addAttribute(HIRE_DATE, hireDate);
    }
}
