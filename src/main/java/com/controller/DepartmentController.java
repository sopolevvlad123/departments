package com.controller;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.utils.RequestDataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.utils.ServletHandlerConstants.*;


@Controller
public class DepartmentController {
    final static Logger logger = Logger.getLogger(DepartmentController.class);


    @Autowired
    private DepartmentService departmentServiceImpl;

    @RequestMapping(value = SAVE_DEPARTMENT, method = RequestMethod.POST)
    public void saveOrUpdateDepartment(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException, ServletException {
        try {
            departmentServiceImpl.saveOrUpdate(buildDepartment(request));
            response.sendRedirect(GET_DEPARTMENT_LIST);
        } catch (ValidationException e) {
            returnFailInput(request);
            logger.error(e);
            request.setAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update department at application layer", e);
        }
    }

    @RequestMapping(value = PREPARE_DEPARTMENT, method = RequestMethod.GET)
    public void prepareDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException {

        String departmentId = request.getParameter(DEPARTMENT_ID);
        if (RequestDataParser.isIDValid(departmentId)) {
            try {
                Department department = departmentServiceImpl.getDepartment(Integer.parseInt(departmentId));
                request.setAttribute(DEPARTMENT_NAME, department.getDepartmentName());
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
        }
        request.getRequestDispatcher(SAVE_DEPARTMENT_PAGE).forward(request, response);

    }

    @RequestMapping(value = DELETE_DEPARTMENT, method = RequestMethod.POST)
    public void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, AppException {
        try {
            departmentServiceImpl.deleteDepartment(Integer.parseInt(request.getParameter(DEPARTMENT_ID)));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete department at application layer", e);
        }
        response.sendRedirect(GET_DEPARTMENT_LIST);
    }


    private Department buildDepartment(HttpServletRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getParameter(DEPARTMENT_NAME));
        if (RequestDataParser.isIDValid(request.getParameter(DEPARTMENT_ID))) {
            department.setDepartmentId(Integer.parseInt(request.getParameter(DEPARTMENT_ID)));
        }
        return department;
    }

    private void returnFailInput(HttpServletRequest request) {
        request.setAttribute(DEPARTMENT_NAME, request.getParameter(DEPARTMENT_NAME));
    }
}
