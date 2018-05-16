package com.controller;

import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.DepartmentService;
import com.utils.DataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String saveOrUpdateDepartment(@RequestParam(DEPARTMENT_ID) String departmentId,
                                         @RequestParam(DEPARTMENT_NAME) String departmentName,
                                         Model model)
            throws AppException {
        try {
            departmentServiceImpl.saveOrUpdate(buildDepartment(departmentName, departmentId));
            model.addAttribute(DEPARTMENT_LIST, departmentServiceImpl.getAllDepartments());
            return "redirect:" + GET_DEPARTMENT_LIST;
        } catch (ValidationException e) {
            model.addAttribute(DEPARTMENT_NAME, departmentName);
            logger.error(e);
            model.addAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            return "saveDepartment";
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update department at application layer", e);
        }
    }

    @RequestMapping(value = PREPARE_DEPARTMENT, method = RequestMethod.GET)
    public String prepareDepartment(@RequestParam(value = DEPARTMENT_ID, required = false) String departmentId,
                                    Model model) throws  AppException {
        if (DataParser.isIDValid(departmentId)) {
            try {
                Department department = departmentServiceImpl.getDepartment(Integer.parseInt(departmentId));
                model.addAttribute(DEPARTMENT_NAME, department.getDepartmentName());
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
        }
        return "saveDepartment";
    }

    @RequestMapping(value = DELETE_DEPARTMENT, method = RequestMethod.POST)
    public String deleteDepartment(@RequestParam(value = DEPARTMENT_ID) String departmentId) throws AppException {
        try {
            departmentServiceImpl.deleteDepartment(Integer.parseInt(departmentId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete department at application layer", e);
        }
        return "redirect:" + GET_DEPARTMENT_LIST;
    }


    private Department buildDepartment(String departmentName, String departmentId) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        if (DataParser.isIDValid(departmentId)) {
            department.setDepartmentId(Integer.parseInt(departmentId));
        }
        return department;
    }

}
