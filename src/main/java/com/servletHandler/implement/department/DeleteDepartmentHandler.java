package com.servletHandler.implement.department;


import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.servlet.FrontControllerServlet;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class DeleteDepartmentHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(DeleteDepartmentHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            departmentService.deleteDepartment(Integer.parseInt(request.getParameter("departmentId")));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete department at application layer", e);
        }
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


}
