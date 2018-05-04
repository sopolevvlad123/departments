package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.EMPLOYEE_LIST_PAGE;

public class DepartmentEmployeeListHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(DepartmentEmployeeListHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        List<Employee> employeeList;
        try {
            employeeList = employeeService.getDepartmentsEmployees(Integer.parseInt(request.getParameter("departmentId")));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to get departments employee at application layer", e);
        }
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("departmentId", Integer.parseInt(request.getParameter("departmentId")));
        request.getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(request, response);
    }
}
