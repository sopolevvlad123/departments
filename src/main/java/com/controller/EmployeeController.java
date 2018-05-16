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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.*;

@Controller
public class EmployeeController {
    final static Logger logger = Logger.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = SAVE_EMPLOYEE, method = RequestMethod.POST)
    public void saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException, ServletException {
        try {
            employeeService.saveOrUpdateEmployee(buildEmployee(request));
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + request.getParameter(DEPARTMENT_ID));
        } catch (ValidationException e) {
            returnFailInput(request);
            logger.error(e);
            request.setAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            request.getRequestDispatcher(SAVE_EMPLOYEE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update employee at application layer", e);
        }
    }

    @RequestMapping(value = GET_DEP_EMPLOYEES, method = RequestMethod.GET)
    public void getDepEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        List<Employee> employeeList;
        try {
            employeeList = employeeService.getDepartmentsEmployees(Integer.parseInt(request.getParameter(DEPARTMENT_ID)));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to get departments employee at application layer", e);
        }
        request.setAttribute(EMPLOYEE_LIST, employeeList);
        request.setAttribute(DEPARTMENT_ID, Integer.parseInt(request.getParameter(DEPARTMENT_ID)));
        request.getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(request, response);
    }

    @RequestMapping(value = DELETE_EMPLOYEE, method = RequestMethod.POST)
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            employeeService.deleteEmployee(Integer.parseInt(request.getParameter(EMPLOYEE_ID)));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete or update employee at application layer", e);
        }
        response.sendRedirect(GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + request.getParameter(DEPARTMENT_ID));
    }

    @RequestMapping(value = PREPARE_EMPLOYEE, method = RequestMethod.GET)
    public void prepareEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        String employeeId = request.getParameter(EMPLOYEE_ID);
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
    }

    private Employee buildEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter(FIRST_NAME));
        employee.setLastName(request.getParameter(LAST_NAME));
        employee.setEmail(request.getParameter(EMAIL));
        employee.setSalary(DataParser.parseInteger(request.getParameter(SALARY)));
        employee.setHireDate(DataParser.parseDate(request.getParameter(HIRE_DATE)));
        employee.setDepartmentId(Integer.parseInt(request.getParameter(DEPARTMENT_ID)));

        if (DataParser.isIDValid(request.getParameter(EMPLOYEE_ID))) {
            employee.setEmployeeId(Integer.parseInt(request.getParameter(EMPLOYEE_ID)));
        }
        return employee;
    }

    private void returnFailInput(HttpServletRequest request) {
        request.setAttribute(FIRST_NAME, request.getParameter(FIRST_NAME));
        request.setAttribute(LAST_NAME, request.getParameter(LAST_NAME));
        request.setAttribute(EMAIL, request.getParameter(EMAIL));
        request.setAttribute(SALARY, request.getParameter(SALARY));
        request.setAttribute(HIRE_DATE, request.getParameter(HIRE_DATE));
    }
}
