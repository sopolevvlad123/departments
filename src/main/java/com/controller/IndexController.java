package com.controller;


import com.bean.Department;
import com.exception.AppException;
import com.exception.ServiceException;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import static com.utils.ServletHandlerConstants.DEFAULT_URL;
import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;


@Controller
public class IndexController {
    @Autowired
    DepartmentService departmentServiceImpl;

    @RequestMapping(value = {DEFAULT_URL, GET_DEPARTMENT_LIST})
    public String hello(Model model) throws AppException {
        List<Department> departmentList;
        try {
            departmentList = departmentServiceImpl.getAllDepartments();
        } catch (ServiceException e) {
            //logger.error(e);
            throw new AppException("Fail to get department list at application layer", e);
        }
        model.addAttribute("departmentList", departmentList);
        System.out.println("INDEX CONTROLL");
        return "index";
    }
}
