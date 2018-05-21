<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>AimprosoftDepartment</title>
</head>
<body>
<div class="container">
    <h1 class="d-flex justify-content-center"> Aimprosoft Department</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">DEPARTMENT NAME</th>
            <th scope="col" colspan="3">ACTIONS</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="department" items="${departmentList}">
            <tr>
                <th scope="row"><c:out value="${department.departmentId}"/></th>
                <td><c:out value="${department.departmentName}"/></td>
                <td>
                    <form action="<c:url value="/deleteDepartment.do"/>" method="post">
                        <button class="btn btn-light" name="departmentId" type="submit"
                                value="${department.departmentId}">Delete
                        </button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/prepareDepartment.do"/>" method="get">
                        <button class="btn btn-light" name="departmentId" type="submit"
                                value="${department.departmentId}">Update
                        </button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/getDepartmentsEmployees.do" />" method="get">
                        <button class="btn btn-light" name="departmentId" type="submit"
                                value="${department.departmentId}">Employees
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row justify-content-md-around">
        <a class="btn btn-success" href=
                "<c:url value="/departmentList.do"/>" role="button">Get Departments</a>

        <a class="btn btn-success" href=
                "<c:url value="/prepareDepartment.do"/>" role="button">Create Department</a>
    </div>
</div>
</body>
</html>