package com.servletHandler;

import com.exception.DAOException;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.impl.DepartmentServiceImpl;
import com.service.impl.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletHandler {

    public DepartmentService   departmentService   = DepartmentServiceImpl.getInstance();
    public EmployeeService     employeeService     = EmployeeServiceImpl.getInstance();

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException, DAOException;


    public void toPreviousPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
