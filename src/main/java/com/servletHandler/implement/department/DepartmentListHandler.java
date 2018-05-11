package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.service.DepartmentService;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.INDEX_PAGE;

@Component(GET_DEPARTMENT_LIST)
public class DepartmentListHandler implements ServletHandler {
    final static Logger logger = Logger.getLogger(DepartmentListHandler.class);

    @Autowired
    private DepartmentService DepartmentServiceImpl;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {
        List<Department> departmentList;
        try {
            System.out.println("depList -- " + DepartmentServiceImpl.getDepartmentByName("wefw1"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            departmentList = DepartmentServiceImpl.getAllDepartments();
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to get department list at application layer", e);
        }
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
    }
}
