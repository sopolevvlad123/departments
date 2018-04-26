package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.DAOException;
import com.exception.ValidationException;
import com.servletHandler.ServletHandler;
import com.utils.RequestDataParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.*;

public class CreateEmployeeHandler extends ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        saveOrUpdateEmployee(buildEmployee(request), request, response, GET_DEP_EMPLOYEES, CREATE_EMPLOYEE_PAGE);

    }

    void saveOrUpdateEmployee(Employee employee, HttpServletRequest request, HttpServletResponse response, String successURL, String failURL) throws ServletException, IOException, DAOException {

        try {
            employeeService.saveOrUpdateEmployee(employee);
        } catch (ValidationException e) {
            e.printStackTrace();
            request.setAttribute("violationMap", e.getViolationsMap());
            toPreviousPage(request, response, failURL);
            return;
        }

        response.sendRedirect(successURL + "?" + request.getSession().getAttribute("departmentIdQuery").toString().trim());
    }

    Employee buildEmployee(HttpServletRequest request) {
        return new Employee(request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                RequestDataParser.parseInteger(request.getParameter("salary")),
                RequestDataParser.parseDate(request.getParameter("hireDate")),
                Integer.parseInt(request.getParameter("departmentId")));
    }
}
