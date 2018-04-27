package com.servletHandler.implement.emploee;

import com.exception.DAOException;
import com.exception.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;
import static com.utils.ServletHandlerConstants.UPDATE_EMPLOYEE_PAGE;

public class UpdateEmployeeHandler extends CreateEmployeeHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        try {
            employeeService.saveOrUpdateEmployee(buildEmployee(request));
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + "departmentId=" + request.getParameter("departmentId"));
        } catch (ValidationException e) {
            request.setAttribute("violationMap", e.getViolationsMap());
            RequestDispatcher dispatcher = request.getRequestDispatcher(UPDATE_EMPLOYEE_PAGE);
            dispatcher.forward(request, response);
        }
    }


}
