package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.service.impl.EmployeeServiceImpl;
import com.servletHandler.ServletHandler;
import com.validator.DepartmentValidator;
import com.validator.EmployeeValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import static com.utils.ServletHandlerConstants.*;

public class CreateEmployeeHandler extends ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveOrUpdateEmployee(buildEmployee(request),request,response,GET_DEP_EMPLOYEES,CREATE_EMPLOYEE_PAGE);

    }

    protected void saveOrUpdateEmployee(Employee employee,HttpServletRequest request, HttpServletResponse response, String successURL, String failURL) throws ServletException, IOException {
        try {
            employeeValidator.validateEmployee(employee);
        } catch (ValidationException e) {
            e.printStackTrace();
            request.setAttribute("violationMap", e.getViolationsMap());
            toPreviousPage(request, response, failURL);
            return;
        }
        employeeService.saveOrUpdateEmployee(buildEmployee(request));
        response.sendRedirect(successURL + "?" + request.getSession().getAttribute("departmentIdQuery").toString().trim());
    }

    private Employee buildEmployee(HttpServletRequest request){
            return new Employee(request.getParameter("firstName"),
                                request.getParameter("lastName"),
                                request.getParameter("email"),
                                requestDataParser.parseInteger(request.getParameter("salary")),
                                requestDataParser.parseDate(request.getParameter("hireDate")),
                                Integer.parseInt(request.getParameter("departmentId")));
    }
}
