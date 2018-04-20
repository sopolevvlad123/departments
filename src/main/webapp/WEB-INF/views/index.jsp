<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>AimprosoftDepartment</title>
</head>
<body>
<h1 class="d-flex justify-content-center"> Aimprosoft Department</h1>
<div class="container">
    <div class="row justify-content-md-center">
        <ul class="list-group">
            <c:forEach var="department" items="${departmentList}">
                <li class="list-group-item">
                    <div class="d-flex justify-content-between">
                        <h5>Department Id :<br> <c:out value="${department.departmentId}"></c:out></h5>
                        <h5>Name :<br><c:out value="${department.departmentName}"></c:out></h5>
                        <form action="/deleteDepartment.do" method="post">
                            <button class="btn btn-primary" name="departmentId" type="submit"
                                    value="${department.departmentId}"><h6>Delete Department</h6></button>
                        </form>
                        <form action="/updateDepartment.jsp" method="get">
                            <input type="hidden" name="departmentName" value="${department.departmentName}"/>
                            <button class="btn btn-primary" name="departmentId" type="submit"
                                    value="${department.departmentId}"><h6>Update Department</h6></button>
                        </form>
                        <form action="/getDepartmentsEmployees.do" method="get">
                            <button class="btn btn-primary" name="departmentId" type="submit"
                                    value="${department.departmentId}"><h6>Employee List</h6></button>
                        </form>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="row justify-content-md-between">
        <a class="btn btn-primary" href="/departmentList.do" role="button">Get Departments</a>
        <a class="btn btn-primary" href="/createDepartment.jsp" role="button">Create Department</a>
    </div>
</div>
</body>
</html>