package com.servletHandler.implement.department;

import com.exception.DAOException;
import com.exception.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.UPDATE_DEPARTMENT_PAGE;


public class UpdateDepartmentHandler extends CreateDepartmentHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        try {
            departmentService.saveOrUpdate(buildDepartment(request));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        } catch (ValidationException e) {
            request.setAttribute("violationMap", e.getViolationsMap());
            RequestDispatcher dispatcher = request.getRequestDispatcher(UPDATE_DEPARTMENT_PAGE);
            dispatcher.forward(request, response);
        }

    }


}
