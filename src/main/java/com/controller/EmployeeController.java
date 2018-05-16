package com.controller;

import com.bean.Employee;
import com.exception.AppException;
import com.exception.ServiceException;
import com.exception.ValidationException;
import com.service.EmployeeService;
import com.utils.DataParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.List;

import static com.utils.ServletHandlerConstants.*;

@Controller
public class EmployeeController {
    final static Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = SAVE_EMPLOYEE, method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee")Employee employee, BindingResult result, ModelMap model) throws  AppException {
        try {
            employeeService.saveOrUpdateEmployee(employee);
            return GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + employee.getDepartmentId();
        } catch (ValidationException e) {
            logger.error(e);
            model.addAttribute(VIOLATIONS_MAP, e.getViolationsMap());
            return "saveEmployee";
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to create or update employee at application layer", e);
        }
    }

    @RequestMapping(value = GET_DEP_EMPLOYEES, method = RequestMethod.GET)
    public String getDepEmployees(@RequestParam(value = DEPARTMENT_ID) String departmentId,Model model) throws  AppException {
        List<Employee> employeeList;
        try {
            employeeList = employeeService.getDepartmentsEmployees(Integer.parseInt(departmentId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to get departments employee at application layer", e);
        }
        model.addAttribute(EMPLOYEE_LIST, employeeList);
        model.addAttribute(DEPARTMENT_ID, departmentId);
        return "employeeList";
    }


    @RequestMapping(value = PREPARE_EMPLOYEE, method = RequestMethod.GET)
    public String getEmployeeForm(@RequestParam(value = EMPLOYEE_ID, required = false) String employeeId,
                                    Model model) throws AppException {
        Employee employee = new Employee();
        if (DataParser.isIDValid(employeeId)) {
            try {
                employee = employeeService.getEmployee(Integer.parseInt(employeeId));
            } catch (ServiceException e) {
                logger.error(e);
                throw new AppException("Fail to get department at application layer", e);
            }
        }
        model.addAttribute("employee", employee);
        return "saveEmployee";
    }

    @RequestMapping(value = DELETE_EMPLOYEE, method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam(value = DEPARTMENT_ID) String departmentId,@RequestParam(value = EMPLOYEE_ID) String employeeId) throws IOException, AppException {
        try {
            employeeService.deleteEmployee(Integer.parseInt(employeeId));
        } catch (ServiceException e) {
            logger.error(e);
            throw new AppException("Fail to delete or update employee at application layer", e);
        }
        return GET_DEP_EMPLOYEES + "?" + DEPARTMENT_ID + "=" + departmentId;
    }
}
