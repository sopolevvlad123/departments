package com.servletHandler;

import com.exception.AppException;
import com.exception.DAOException;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.impl.DepartmentServiceImpl;
import com.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface  ServletHandler {

     DepartmentService   departmentService   = DepartmentServiceImpl.getInstance();
     EmployeeService     employeeService     = EmployeeServiceImpl.getInstance();

     void execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException, AppException;


}
