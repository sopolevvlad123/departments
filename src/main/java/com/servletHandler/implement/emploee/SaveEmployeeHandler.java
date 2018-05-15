package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.servletHandler.ServletHandler;
import com.utils.DataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.*;


@Component(SAVE_EMPLOYEE)
public class SaveEmployeeHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(SaveEmployeeHandler.class);
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        try {
            System.out.println("From body");
            employeeService.saveOrUpdateEmployee(buildEmployee(request));
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + "departmentId=" + request.getParameter("departmentId"));
        } catch (ValidationException e) {
            returnFailInput(request);
            logger.error(e);
            request.setAttribute("violationMap", e.getViolationsMap());
            request.getRequestDispatcher(SAVE_EMPLOYEE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update employee at application layer", e);
        }
    }

    private Employee buildEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setEmail(request.getParameter("email"));
        employee.setSalary(DataParser.parseInteger(request.getParameter("salary")));
        employee.setHireDate(DataParser.parseDate(request.getParameter("hireDate")));
        employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

        if (DataParser.isIDValid(request.getParameter("employeeId"))) {
            employee.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
        }
        return employee;
    }

    private void returnFailInput(HttpServletRequest request) {
        request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("lastName", request.getParameter("lastName"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("salary", request.getParameter("salary"));
        request.setAttribute("hireDate", request.getParameter("hireDate"));
    }
}
