package com.utils;

public interface ServletHandlerConstants {
    String DEFAULT_URL = "/";
    String GET_DEPARTMENT_LIST = "/departmentList.do";
    String GET_DEP_EMPLOYEES = "/getDepartmentsEmployees.do";
    String SAVE_DEPARTMENT = "/saveDepartment.do";
    String DELETE_DEPARTMENT = "/deleteDepartment.do";
    String PREPARE_DEPARTMENT = "/prepareDepartment.do";


    String SAVE_EMPLOYEE = "/saveEmployee.do";
    String DELETE_EMPLOYEE = "/deleteEmployee.do";
    String PREPARE_EMPLOYEE = "/prepareEmployee.do";

    String ERROR_PAGE = "/WEB-INF/views/errorPage.jsp";
    String SAVE_DEPARTMENT_PAGE = "/WEB-INF/views/saveDepartment.jsp";
    String SAVE_EMPLOYEE_PAGE = "/WEB-INF/views/saveEmployee.jsp";
    String EMPLOYEE_LIST_PAGE = "/WEB-INF/views/employeeList.jsp";
    String INDEX_PAGE = "WEB-INF/views/index.jsp";
    String PAGE_404 ="/WEB-INF/views/page404.jsp";



    String DEPARTMENT_ID = "departmentId";
    String DEPARTMENT_NAME = "departmentName";
    String DEPARTMENT_LIST = "departmentList";


    String EMPLOYEE_ID = "employeeId";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String EMAIL = "email";
    String HIRE_DATE = "hireDate";
    String SALARY = "salary";
    String EMPLOYEE_LIST = "employeeList";

    String VIOLATIONS_MAP = "violationMap";

}
