package com.servletHandler;

import com.bean.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.impl.DepartmentServiceImpl;
import com.service.impl.EmployeeServiceImpl;
import com.utils.RequestDataParser;
import com.validator.DepartmentValidator;
import com.validator.EmployeeValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletHandler {
    public DepartmentService   departmentService   = DepartmentServiceImpl.getInstance();
    public EmployeeService     employeeService     = EmployeeServiceImpl.getInstance();
    public EmployeeValidator   employeeValidator   = new EmployeeValidator();
    public DepartmentValidator departmentValidator = new DepartmentValidator();
    public RequestDataParser   requestDataParser   = new RequestDataParser();


    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException;


    public void toPreviousPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
