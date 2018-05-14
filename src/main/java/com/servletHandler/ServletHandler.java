package com.servletHandler;

import com.exception.AppException;
import com.exception.DAOException;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.impl.DepartmentServiceImpl;
import com.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public interface  ServletHandler {
     void execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException, AppException;
}
