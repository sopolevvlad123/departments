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
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="firstNameHelp"
                    value="${param.firstName}">
            <small id="firstNameHelp" class="form-text text-muted"><c:out
                    value="${firstNameViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="lastNameHelp"
                    value="${param.lastName}">
            <small id="lastNameHelp" class="form-text text-muted"><c:out value="${lastNameViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                    value="${param.email}">
            <small id="emailHelp" class="form-text text-muted"><c:out value="${emailUnique}"></c:out>
                <c:out value="${emailViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="salary">Salary</label>
            <input type="text" class="form-control" id="salary" name="salary" aria-describedby="salaryHelp"
                   value="${param.salary}">
            <small id="salaryHelp" class="form-text text-muted"><c:out value="${salaryViolations}"></c:out></small>
        </div>
        <div class="form-group">
            <label for="hireDate">Hire Date</label>
            <input type="date" class="form-control" id="hireDate" name="hireDate" aria-describedby="hireDateHelp"
                    value="${param.hireDate}">
            <small id="hireDateHelp" class="form-text text-muted"><c:out value="${hireDateViolations}"></c:out></small>
        </div>
        <input type="hidden" name="employeeId" value="${param.employeeId}"/>
        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
