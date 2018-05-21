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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.utils.ServletHandlerConstants.*;


@Controller
public class DepartmentController {
    private final static Logger logger = Logger.getLogger(DepartmentController.class);


    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = SAVE_DEPARTMENT, method = RequestMethod.POST)
    public String saveOrUpdateDepartment(@ModelAttribute("department") Department department, ModelMap model) throws AppException {
        try {
            departmentService.saveOrUpdate(department);
            model.addAttribute(DEPARTMENT_LIST, departmentService.getAllDepartments());
            return "redirect:" + GET_DEPARTMENT_LIST;
        } catch (ValidationException e) {
            logger.error(e);
            model.addAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            return "saveDepartment";
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update department at application layer", e);
        }
    }

    @RequestMapping(value = PREPARE_DEPARTMENT, method = RequestMethod.GET)
    public String getDepartmentForm(@RequestParam(value = DEPARTMENT_ID, required = false) String departmentId,
                                    Model model) throws AppException {
        Department department = new Department();
        if (DataParser.isIDValid(departmentId)) {
            try {
                department = departmentService.getDepartment(Integer.parseInt(departmentId));
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
        }
        model.addAttribute("department", department);
        return "saveDepartment";
    }

    @RequestMapping(value = DELETE_DEPARTMENT, method = RequestMethod.POST)
    public String deleteDepartment(@RequestParam(value = DEPARTMENT_ID) String departmentId) throws AppException {
        try {
            departmentService.deleteDepartment(Integer.parseInt(departmentId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete department at application layer", e);
        }
        return "redirect:" + GET_DEPARTMENT_LIST;
    }
}
