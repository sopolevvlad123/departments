package com.servletHandler.implement.emploee;

import com.service.EmployeeService;
import com.servletHandler.implement.AbstractCreateServletHanler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import static com.utils.ServletHandlerConstants.CREATE_EMPLOYEE_PAGE;
import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;

public class CreateEmployeeHandler extends AbstractCreateServletHanler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> violationsMap = EmployeeService.getInstance().validationProblemsMap(request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                request.getParameter("salary"),
                request.getParameter("hireDate"),
                request.getParameter("departmentId")
                );
        if (violationsMap.size()>0){
            request.setAttribute("violationMap", violationsMap);
            toPreviousPage(request, response, CREATE_EMPLOYEE_PAGE);
        }else {
            EmployeeService.getInstance().createEmployee(request.getParameter("email"), request.getParameter("firstName"),
                    request.getParameter("lastName"), Integer.parseInt(request.getParameter("salary")),
                    Date.valueOf(request.getParameter("hireDate")), Integer.parseInt(request.getParameter("departmentId")));
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + request.getSession().getAttribute("departmentIdQuery").toString().trim());
        }
    }
    @Override
    public boolean isBeanValid(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isBeanUnique(HttpServletRequest request) {
        return false;
    }
}
