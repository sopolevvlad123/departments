<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Update Employee</title>
</head>
<body>

<div class="container">
    <h2>Update Employee Form</h2>
    <c:out value="${param.employeeId}"></c:out>
    <form action="/updateEmployee.do" method="post">
        <div class="form-group row">
            <label for="firstName" class="col-2 col-form-label">First Name</label>
            <div class="col-6">
                <input class="form-control" type="text" name="firstName" id="firstName" value="${param.firstName}">
            </div>
            <div>
                <c:out value="${violationMap.firstNameViolations}"></c:out>
            </div>
        </div>

        <div class="form-group row">
            <label for="lastName" class="col-2 col-form-label">Last Name</label>
            <div class="col-6">
                <input class="form-control" type="text" name="lastName" id="lastName" value="${param.lastName}">
            </div>
            <div>
                <c:out value="${violationMap.lastNameViolations}"></c:out>
            </div>
        </div>

        <div class="form-group row">
            <label for="email" class="col-2 col-form-label">Email</label>
            <div class="col-6">
                <input class="form-control" type="text" name="email" id="email" value="${param.email}">
            </div>
            <div>
                <c:out value="${violationMap.emailViolations}"></c:out>
            </div>
        </div>

        <div class="form-group row">
            <label for="salary" class="col-2 col-form-label">Salary</label>
            <div class="col-6">
                <input class="form-control" type="text" name="salary" id="salary" value="${param.salary}">
            </div>
            <div>
                <c:out value="${violationMap.salaryViolations}"></c:out>
            </div>
        </div>

        <div class="form-group row">
            <label for="hireDate" class="col-2 col-form-label">Hire Date</label>
            <div class="col-6">
                <input class="form-control" type="date" name="hireDate" id="hireDate" value="${param.hireDate}">
            </div>
        </div>

        <input type="hidden" name="employeeId" value="${param.employeeId}"/>
        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
