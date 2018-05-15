package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.service.DepartmentService;
import com.servletHandler.ServletHandler;
import com.utils.DataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.PREPARE_DEPARTMENT;
import static com.utils.ServletHandlerConstants.SAVE_DEPARTMENT_PAGE;

@Component(PREPARE_DEPARTMENT)
public class PrepareDepartmentHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(PrepareDepartmentHandler.class);
    @Autowired
    private DepartmentService departmentServiceImpl;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        String departmentId = request.getParameter("departmentId");
        if (DataParser.isIDValid(departmentId)) {
            try {
                Department department = departmentServiceImpl.getDepartment(Integer.parseInt(departmentId));
                request.setAttribute("departmentName", department.getDepartmentName());
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
        }
        request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE).forward(request, response);

    }
}
