package com.servletHandler.implement.department;

import com.service.DepartmentService;
import com.servletHandler.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.utils.ServletHandlerConstants.CREATE_DEPARTMENT_PAGE;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


public class CreateDepartmentHandler extends ServletHandler {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> violationsMap = DepartmentService.getInstance().validationProblemsMap(request.getParameter("departmentName"));
        if (violationsMap.size()>0){
            request.setAttribute("violationMap", violationsMap);
            toPreviousPage(request, response, CREATE_DEPARTMENT_PAGE);
        }else {
            DepartmentService.getInstance().createDepartment(request.getParameter("departmentName"));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        }
    }


}
