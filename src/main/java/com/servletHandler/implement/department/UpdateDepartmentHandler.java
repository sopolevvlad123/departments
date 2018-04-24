package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.ValidationException;
import com.servletHandler.ServletHandler;
import com.validator.DepartmentValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.utils.ServletHandlerConstants.CREATE_DEPARTMENT_PAGE;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.UPDATE_DEPARTMENT_PAGE;


public class UpdateDepartmentHandler extends ServletHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentValidator departmentValidator = new DepartmentValidator();
        try {
            departmentValidator.validateDepartment(buildDepartment(request.getParameter("departmentName"),request.getParameter("departmentId")));
        } catch (ValidationException e) {
            e.printStackTrace();
            request.setAttribute("violationMap", e.getViolationsMap());
            toPreviousPage(request, response, CREATE_DEPARTMENT_PAGE);
            return;
        }
        departmentService.saveOrUpdate(buildDepartment(request.getParameter("departmentName"),request.getParameter("departmentId")));
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }

    private Department buildDepartment(String departmentName, String departmentId){
        return new Department(Integer.parseInt(departmentId),departmentName);
    }

}
