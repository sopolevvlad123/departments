package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.ServiceException;
import com.servletHandler.ServletHandler;
import com.utils.RequestDataParser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.SAVE_EMPLOYEE_PAGE;

public class PrepareEmployeeHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(PrepareEmployeeHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        String employeeId = request.getParameter("employeeId");
        if (RequestDataParser.isIDValid(employeeId)) {
            try {
                Employee employee = employeeService.getEmployee(Integer.parseInt(employeeId));
                request.setAttribute("firstName", employee.getFirstName());
                request.setAttribute("lastName", employee.getLastName());
                request.setAttribute("email", employee.getEmail());
                request.setAttribute("salary", employee.getSalary());
                request.setAttribute("hireDate", employee.getHireDate());
                request.setAttribute("departmentId", employee.getDepartmentId());
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get employee at application layer", e);
            }
        }
        request.getRequestDispatcher(SAVE_EMPLOYEE_PAGE).forward(request, response);
    }
}
