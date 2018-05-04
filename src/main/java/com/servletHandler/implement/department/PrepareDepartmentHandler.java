package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.servletHandler.ServletHandler;
import com.utils.RequestDataParser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.SAVE_DEPARTMENT_PAGE;

public class PrepareDepartmentHandler implements ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        String departmentId = request.getParameter("departmentId");
        if (RequestDataParser.isIDValid(departmentId)) {
            try {
                Department department = departmentService.getDepartment(Integer.parseInt(departmentId));
                request.setAttribute("departmentName", department.getDepartmentName());
            } catch (ServiceException e) {
                Logger log = (Logger) request.getSession().getServletContext().getAttribute("appLogger");
                log.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE);
            dispatcher.forward(request, response);
        }

    }
}
