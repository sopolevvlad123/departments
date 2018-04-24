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
   <%-- <form action="/updateEmployee.do" method="post">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="firstNameHelp"
                   placeholder="First Name" value="${param.firstName}">
            <small id="firstNameHelp" class="form-text text-muted"><c:out value="${violationMap.firstNameViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="lastNameHelp"
                   placeholder="Last Name" value="${param.lastName}">
            <small id="lastNameHelp" class="form-text text-muted"><c:out value="${violationMap.lastNameViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                   placeholder="Email" value="${param.email}">
            <small id="emailHelp" class="form-text text-muted">
                <c:out value="${violationMap.emailViolations}"></c:out>
            </small>
        </div>
        <div class="form-group">
            <label for="salary">Salary</label>
            <input type="text" class="form-control" id="salary" name="salary" aria-describedby="salaryHelp"
                   placeholder="Salary" value="${param.salary}">
            <small id="salaryHelp" class="form-text text-muted"><c:out value="${violationMap.salaryViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="hireDate">Hire Date</label>
            <input type="date" class="form-control" id="hireDate" name="hireDate" aria-describedby="hireDateHelp"
                   placeholder="Hire date" value="${param.hireDate}">
            <small id="hireDateHelp" class="form-text text-muted"><c:out value="${violationMap.hireDateViolations}"></c:out></small>
        </div>
        <input type="hidden" name="employeeId" value="${param.employeeId}"/>
        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>--%>
</div>

</body>
</html>
