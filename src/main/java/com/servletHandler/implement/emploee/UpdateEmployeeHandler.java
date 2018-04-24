package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.service.impl.EmployeeServiceImpl;
import com.servletHandler.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;
import static com.utils.ServletHandlerConstants.UPDATE_EMPLOYEE_PAGE;

public class UpdateEmployeeHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       /* Map<String,String> violationsMap = EmployeeServiceImpl.getInstance().validationProblemsMap(request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                request.getParameter("salary"),
                request.getParameter("hireDate"),
                request.getParameter("departmentId"),
                request.getParameter("employeeId")
        );
        if (violationsMap.size()>0){
            request.setAttribute("violationMap", violationsMap);
            toPreviousPage(request, response, UPDATE_EMPLOYEE_PAGE);

        } else {
            Employee employee = new Employee(Integer.parseInt(request.getParameter("employeeId")),
                    request.getParameter("firstName"),
                    request.getParameter("lastName"),
                    request.getParameter("email"),
                    Integer.parseInt(request.getParameter("salary")),
                    Date.valueOf(request.getParameter("hireDate")),
                    Integer.parseInt(request.getParameter("departmentId")));
            EmployeeServiceImpl.getInstance().updateEmployee(employee);
            response.sendRedirect(GET_DEP_EMPLOYEES + "?" + request.getSession().getAttribute("departmentIdQuery").toString().trim());
        }*/


    }
}
