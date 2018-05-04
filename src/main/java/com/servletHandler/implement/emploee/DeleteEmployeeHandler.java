package com.servletHandler.implement.emploee;

import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.service.impl.EmployeeServiceImpl;
import com.servletHandler.ServletHandler;
import com.servletHandler.implement.department.DeleteDepartmentHandler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;

public class DeleteEmployeeHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(DeleteEmployeeHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            EmployeeServiceImpl.getInstance().deleteEmployee(Integer.parseInt(request.getParameter("employeeId")));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete or update employee at application layer", e);
        }
        response.sendRedirect(GET_DEP_EMPLOYEES + "?" + "departmentId=" + request.getParameter("departmentId") );
    }


}
