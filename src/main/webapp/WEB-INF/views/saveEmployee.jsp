<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Create Employee</title>
</head>
<body>

<div class="container">

    <h2>Save Employee Form</h2>
    <%--@elvariable id="employee" type="com.bean.Employee"--%>
    <form:form method="POST" action="/saveEmployee.do" modelAttribute="employee">
        <div class="form-group row">
            <label class="col-2 col-form-label">First Name</label>
            <div class="col-6">
                <form:input path="firstName"/>
            </div>
            <div>
                    <%--@elvariable id="violationMap" type="java.util.HashMap"--%>
                <c:out value="${violationMap.firstName}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">Last Name</label>
            <div class="col-6">
                <form:input path="lastName"/>
            </div>
            <div>
                <c:out value="${violationMap.lastName}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">Email</label>
            <div class="col-6">
                <form:input path="email"/>
            </div>
            <div>
                <c:out value="${violationMap.email}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">Salary</label>
            <div class="col-6">
                <form:input path="salary"/>
            </div>
            <div>
                <c:out value="${violationMap.salary}"/>

            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">Hire Date</label>
            <div class="col-6">
                <form:input path="hireDate" type="date"/>
            </div>
            <div>
                <c:out value="${violationMap.hireDate}"/>
            </div>
        </div>


        <form:hidden path="departmentId" value="${employee.departmentId}"/>
        <form:hidden path="employeeId" value="${employee.employeeId}"/>
        <div class="row justify-content-md-between">
            <button type="submit" class="btn btn-primary">Submit</button>

        </div>


    </form:form>

    <form action=
                  "<c:url value="/getDepartmentsEmployees.do"/>" method="get">
        <div class="form-group row">
            <button class="btn btn-success" name="departmentId" value="${param.departmentId}">Employee List</button>
        </div>
    </form>

</div>

</body>
</html>
