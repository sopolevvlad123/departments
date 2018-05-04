<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Create Employee</title>
</head>
<body>

<div class="container">

    <h2>Save Employee Form</h2>

    <form action=
          <c:url value="/saveEmployee.do"/> method="post">
        <div class="form-group row">
            <label for="firstName" class="col-2 col-form-label">First Name</label>
            <div class="col-6">
                <input class="form-control" type="text" name="firstName" id="firstName" value="${firstName}">
            </div>
            <div>
                <c:out value="${violationMap.firstName}"></c:out>
            </div>
        </div>
        <div class="form-group row">
            <label for="lastName" class="col-2 col-form-label">Last Name</label>
            <div class="col-6">
                <input class="form-control" type="text" name="lastName" id="lastName" value="${lastName}">
            </div>
            <div>
                <c:out value="${violationMap.lastName}"></c:out>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-2 col-form-label">Email</label>
            <div class="col-6">
                <input class="form-control" type="text" name="email" id="email" value="${email}">
            </div>
            <div>
                <c:out value="${violationMap.email}"></c:out>
            </div>
        </div>
        <div class="form-group row">
            <label for="salary" class="col-2 col-form-label">Salary</label>
            <div class="col-6">
                <input class="form-control" type="text" name="salary" id="salary" value="${salary}">
            </div>
            <div>
                <c:out value="${violationMap.salary}"></c:out>
            </div>
        </div>
        <div class="form-group row">
            <label for="hireDate" class="col-2 col-form-label">Hire Date</label>
            <div class="col-6">
                <input class="form-control" type="date" name="hireDate" id="hireDate" value="${hireDate}">
            </div>
            <div>
                <c:out value="${violationMap.hireDate}"></c:out>
            </div>
        </div>

        <input type="hidden" name="employeeId" value="${param.employeeId}"/>
        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
        <div class="row justify-content-md-between">
            <button type="submit" class="btn btn-primary">Submit</button>

        </div>

    </form>

    <form action=<c:url value= "/getDepartmentsEmployees.do"/>  method="get">
        <div class="form-group row">
            <button class="btn btn-success" name="departmentId" type="departmentId"
                    value="${param.departmentId}"><h6>Employee List</h6></button>
        </div>

    </form>

</div>

</body>
</html>
