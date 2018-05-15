package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
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

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.SAVE_DEPARTMENT;
import static com.utils.ServletHandlerConstants.SAVE_DEPARTMENT_PAGE;


@Component(SAVE_DEPARTMENT)
public class SaveDepartmentHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(SaveDepartmentHandler.class);
    @Autowired
    private DepartmentService departmentServiceImpl;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        try {
            departmentServiceImpl.saveOrUpdate(buildDepartment(request));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        } catch (ValidationException e) {
            returnFailInput(request);
            logger.error(e);
            request.setAttribute("violationMap", e.getViolationsMap());
            request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update department at application layer", e);
        }
    }

     private Department buildDepartment(HttpServletRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getParameter("departmentName"));
        if (DataParser.isIDValid(request.getParameter("departmentId"))) {
            department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
        }
        return department;
    }

    private void returnFailInput(HttpServletRequest request) {
        request.setAttribute("departmentName", request.getParameter("departmentName"));
    }
}
