package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.service.impl.DepartmentServiceImpl;
import com.servletHandler.ServletHandler;
import com.validator.DepartmentValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.CREATE_DEPARTMENT_PAGE;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class CreateDepartmentHandler extends ServletHandler {
    private DepartmentService departmentService = DepartmentServiceImpl.getInstance();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentValidator departmentValidator = new DepartmentValidator();
        try {
            departmentValidator.validateDepartment(buildDepartment(request.getParameter("departmentName")));
        } catch (ValidationException e) {
            e.printStackTrace();
            request.setAttribute("violationMap", e.getViolationsMap());
            toPreviousPage(request, response, CREATE_DEPARTMENT_PAGE);
            return;
        }
        departmentService.saveOrUpdate(buildDepartment(request.getParameter("departmentName")));
        response.sendRedirect(GET_DEPARTMENT_LIST);

    }

    private Department buildDepartment(String departmentName){
        return new Department(departmentName);
    }


}
