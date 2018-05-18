package com.utils;

public interface HibernateConstants {

    String FROM_EMPLOYEE_BY_DEP_ID = "FROM Employee where departmentId=:departmentId order by employeeId";
    String FROM_EMPLOYEE_BY_EMAIL = "FROM Employee where email=:email";

    String FROM_DEPARTMENT = "FROM Department ORDER BY departmentId";
    String FROM_DEPARTMENT_BY_NAME = "FROM Department where departmentName=:departmentName";
}
