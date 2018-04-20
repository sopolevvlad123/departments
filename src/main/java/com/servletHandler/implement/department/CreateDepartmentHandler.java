package com.servletHandler.implement.department;

import com.service.DepartmentService;
import com.servletHandler.implement.AbstractCreateServletHanler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.CREATE_DEPARTMENT_PAGE;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class CreateDepartmentHandler extends AbstractCreateServletHanler {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!isBeanValid(request)) {
            toPreviousPage(request, response, CREATE_DEPARTMENT_PAGE);
        }/*else if (!isBeanUnique(request)){
            request.setAttribute("depNameUnique", "Department with this name already exist");
            toPreviousPage(request, response, CREATE_DEPARTMENT_PAGE);
        }*/else {
            DepartmentService.getInstance().createDepartment(request.getParameter("departmentName"));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        }
    }

    @Override
    public boolean isBeanValid(HttpServletRequest request) {
        /*String departmentName = request.getParameter("departmentName");
        Department department = new Department(departmentName);
        List<ConstraintViolation> violations = DepartmentService.getInstance().validationProblemsList(department);
        if (violations.size() > 0) {
            request.setAttribute("departmentViolations", violations);
            return false;
        }*/
        return true;
    }

    @Override
    public boolean isBeanUnique(HttpServletRequest request) {
        return DepartmentService.getInstance().checkUnique(request.getParameter("departmentName"));
    }


}
