package com.servletHandler.implement.department;


import com.exception.AppException;
import com.exception.ServiceException;
import com.service.DepartmentService;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.DELETE_DEPARTMENT;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;

@Component(DELETE_DEPARTMENT)
public class DeleteDepartmentHandler implements ServletHandler {
    @Autowired
    private DepartmentService departmentServiceImpl;
    final static Logger logger = Logger.getLogger(DeleteDepartmentHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            departmentServiceImpl.deleteDepartment(Integer.parseInt(request.getParameter("departmentId")));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete department at application layer", e);
        }
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


}
