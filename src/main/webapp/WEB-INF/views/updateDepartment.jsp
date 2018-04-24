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
    <c:out value="${param.departmentId}"></c:out>

    <form action="/updateDepartment.do" method="post">

        <div class="form-group row">
            <label for="departmentName" class="col-2 col-form-label">New Name</label>
            <div class="col-6">
                <input type="hidden" name="departmentId" value="${param.departmentId}"/>
                <input class="form-control" type="text" name="departmentName" id="departmentName"
                       value="${param.departmentName}">
            </div>
            <div>
                <c:out value="${violationMap.departmentNameViolation}"></c:out>
            </div>
        </div>

        <div class="row justify-content-md-between">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a class="btn btn-success" href="/departmentList.do" role="button">Main Page</a>
        </div>
    </form>


    <%--
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
        </div>--%>
</div>
</body>
</html>
