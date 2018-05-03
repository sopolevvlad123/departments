package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.servletHandler.ServletHandler;
import com.utils.RequestDataParser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.*;

public class SaveEmployeeHandler implements ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        try {
            employeeService.saveOrUpdateEmployee(buildEmployee(request));
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + "departmentId=" + request.getParameter("departmentId"));
        } catch (ValidationException e) {
            returnFailInput(request);
            Logger log = (Logger) request.getSession().getServletContext().getAttribute("appLogger");
            log.error(e);
            request.setAttribute("violationMap", e.getViolationsMap());
            RequestDispatcher dispatcher = request.getRequestDispatcher(SAVE_EMPLOYEE_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            Logger log = (Logger) request.getSession().getServletContext().getAttribute("appLogger");
            log.error(e);
            throw new AppException("Fail to create or update employee at application layer", e);
        }
    }

    private Employee buildEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setEmail(request.getParameter("email"));
        employee.setSalary(RequestDataParser.parseInteger(request.getParameter("salary")));
        employee.setHireDate(RequestDataParser.parseDate(request.getParameter("hireDate")));
        employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

        if (!(request.getParameter("employeeId").isEmpty())) {
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
