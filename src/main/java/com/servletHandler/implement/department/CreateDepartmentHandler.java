package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.DAOException;
import com.exception.ValidationException;
import com.servletHandler.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.CREATE_DEPARTMENT_PAGE;


public class CreateDepartmentHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        saveOrUpdateDepartment(buildDepartment(request), request, response, CREATE_DEPARTMENT_PAGE);
    }

     void saveOrUpdateDepartment(Department department, HttpServletRequest request, HttpServletResponse response, String unSuccessURL) throws ServletException, IOException, DAOException {
         try {
             departmentService.saveOrUpdate(department);
         } catch (ValidationException e) {
             e.printStackTrace();
             request.setAttribute("violationMap", e.getViolationsMap());
             toPreviousPage(request, response, unSuccessURL);
             return;
         }
        //departmentService.saveOrUpdate(department);
        response.sendRedirect(com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST);
    }

     Department buildDepartment(HttpServletRequest request) {
        return new Department(request.getParameter("departmentName"));
    }
}
