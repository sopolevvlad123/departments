package com.servletHandler.implement.department;


import com.servletHandler.ServletHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class DeleteDepartmentHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        departmentService.deleteDepartment(Integer.parseInt(request.getParameter("departmentId")));
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


}
