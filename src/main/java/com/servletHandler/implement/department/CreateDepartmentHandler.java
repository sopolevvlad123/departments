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
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class CreateDepartmentHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        saveOrUpdateDepartment(buildDepartment(request), request, response, CREATE_DEPARTMENT_PAGE, GET_DEPARTMENT_LIST);
    }

     void saveOrUpdateDepartment(Department department, HttpServletRequest request, HttpServletResponse response,
                                 String unSuccessURL, String successURL) throws ServletException, IOException, DAOException {
         try {
             departmentService.saveOrUpdate(department);
         } catch (ValidationException e) {
             e.printStackTrace();
             request.setAttribute("violationMap", e.getViolationsMap());
             toPreviousPage(request, response, unSuccessURL);
             return;
         }
        response.sendRedirect(successURL);
    }

     Department buildDepartment(HttpServletRequest request) {
        return new Department(request.getParameter("departmentName"));
    }
}
