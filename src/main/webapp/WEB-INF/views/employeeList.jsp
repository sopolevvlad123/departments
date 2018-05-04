<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title> Employee List</title>
</head>
<body>
<h1 class="d-flex justify-content-center">Employee List</h1>
<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">FIRST NAME</th>
            <th scope="col">LAST NAME</th>
            <th scope="col">EMAIL</th>
            <th scope="col">SALARY</th>
            <th scope="col">HIRE DATE</th>
            <th scope="col" colspan="2">ACTIONS</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <th scope="row"><c:out value="${employee.employeeId}"></c:out></th>
                <td><c:out value="${employee.firstName}"></c:out></td>
                <td><c:out value="${employee.lastName}"></c:out></td>
                <td><c:out value="${employee.email}"></c:out></td>
                <td><c:out value="${employee.salary}"></c:out></td>
                <td><c:out value="${employee.hireDate}"></c:out></td>
                <td>
                    <form action=<c:url value= "/deleteEmployee.do"/>  method="post">
                        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
                        <button class="btn btn-light" name="employeeId" type="submit"
                                value="${employee.employeeId}"><h6>Delete Employee</h6></button>
                    </form>
                </td>
                <td>
                    <form action=<c:url value= "/prepareEmployee.do"/> method="get">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}"/>
                        <button class="btn btn-light" name="departmentId" value="${departmentId}" type="submit">Update Employee</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row justify-content-md-around">
        <form action = <c:url value= "/prepareEmployee.do"/> method="get">
            <button class="btn btn-success" name="departmentId" type="submit" value="${departmentId}">
                <h6>Create Employee</h6></button>
        </form>
        <div>
            <a class="btn btn-success" href=<c:url value= "/departmentList.do"/> role="button">Main Page</a>
        </div>
    </div>
</div>
</body>
</html>
