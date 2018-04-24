package com.servletHandler.implement.department;

import com.bean.Department;
import com.servletHandler.ServletHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.INDEX_PAGE;

public class DepartmentListHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = departmentService.getAllDepartments();
        request.setAttribute("departmentList", departmentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX_PAGE);
        dispatcher.forward(request, response);
    }


}
