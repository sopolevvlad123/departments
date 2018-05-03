package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.SAVE_DEPARTMENT_PAGE;


public class SaveDepartmentHandler implements ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        try {
            departmentService.saveOrUpdate(buildDepartment(request));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        } catch (ValidationException e) {
            returnFailInput(request);
            Logger log = (Logger) request.getSession().getServletContext().getAttribute("appLogger");
            log.error(e);
            request.setAttribute("violationMap", e.getViolationsMap());
            RequestDispatcher dispatcher = request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            Logger log = (Logger) request.getSession().getServletContext().getAttribute("appLogger");
            log.error(e);
            throw new AppException("Fail to create or update department at application layer", e);
        }
    }

     private Department buildDepartment(HttpServletRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getParameter("departmentName"));
        if (!(request.getParameter("departmentId").isEmpty())) {
            department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
        }
        return department;
    }

    private void returnFailInput(HttpServletRequest request) {
        request.setAttribute("departmentName", request.getParameter("departmentName"));
    }
}
