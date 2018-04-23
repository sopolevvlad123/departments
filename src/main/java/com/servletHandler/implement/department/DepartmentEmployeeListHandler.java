package com.servletHandler.implement.department;

import com.bean.Employee;
import com.service.EmployeeService;
import com.servletHandler.ServletHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.EMPLOYEE_LIST_PAGE;

public class DepartmentEmployeeListHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = EmployeeService.getInstance().getEmployeesByDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("departmentId", Integer.parseInt(request.getParameter("departmentId")));
        RequestDispatcher dispatcher = request.getRequestDispatcher(EMPLOYEE_LIST_PAGE);
        dispatcher.forward(request, response);
    }
}
