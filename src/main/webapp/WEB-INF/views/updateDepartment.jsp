<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Update Department</title>
</head>
<body>
<div class="container">
    <h2>Update Department Form</h2>
    <form action="/updateDepartment.do" method="post">
        <div class="form-group">
            <label for="departmentName"> Enter new name </label>
            <input type="hidden" name="departmentId" value="${param.departmentId}"/>
            <input type="text" class="form-control" id="departmentName" name="departmentName"
                   aria-describedby="nameHelp" value="${param.departmentName}">
            <small id="nameHelp" class="form-text text-muted">
                <c:out value="${violationMap.departmentNameViolation}"></c:out>
            </small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div class="row justify-content-md-between">
        <a class="btn btn-primary" href="/departmentList.do" role="button">Main Page</a>
    </div>
</div>
</body>
</html>
