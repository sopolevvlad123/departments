package com.servletHandler.implement.department;


import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.servletHandler.ServletHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class DeleteDepartmentHandler implements ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            departmentService.deleteDepartment(Integer.parseInt(request.getParameter("departmentId")));
        } catch (ServiceException e) {
            throw new AppException("Fail to delete department at handler layer", e);

        }
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


}
