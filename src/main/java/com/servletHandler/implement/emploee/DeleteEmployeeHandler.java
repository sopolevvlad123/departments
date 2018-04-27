package com.servletHandler.implement.emploee;

import com.exception.DAOException;
import com.service.impl.EmployeeServiceImpl;
import com.servletHandler.ServletHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;

public class DeleteEmployeeHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException {
        EmployeeServiceImpl.getInstance().deleteEmployee(Integer.parseInt(request.getParameter("employeeId")));

        response.sendRedirect(GET_DEP_EMPLOYEES + "?" + "departmentId=" + request.getParameter("departmentId") );

        //response.sendRedirect(GET_DEP_EMPLOYEES + "?" + request.getSession().getAttribute("departmentIdQuery").toString().trim());
    }


}
