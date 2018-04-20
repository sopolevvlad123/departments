package com.servletHandler.implement.department;


import com.service.DepartmentService;
import com.servletHandler.ServletHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class DeleteDepartmentHandler implements ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        DepartmentService.getInstance().deleteDepartment(Integer.parseInt(request.getParameter("departmentId")));
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


}
