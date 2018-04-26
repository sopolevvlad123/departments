package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.DAOException;
import com.servletHandler.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.INDEX_PAGE;

public class DepartmentListHandler extends ServletHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        List<Department> departmentList = departmentService.getAllDepartments();
        request.setAttribute("departmentList", departmentList);
        toPreviousPage(request,response,INDEX_PAGE);
    }


}
