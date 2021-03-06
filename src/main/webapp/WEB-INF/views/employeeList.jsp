<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title> Employee List</title>
</head>
<body>
<h1 class="d-flex justify-content-center">Employee List</h1>
<c:set var="departmentIdQuery" scope="session" value=" ${requestScope['javax.servlet.forward.query_string']}"></c:set>
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
                    <form action="/deleteEmployee.do" method="post">
                        <button class="btn btn-light" name="employeeId" type="submit"
                                value="${employee.employeeId}"><h6>Delete Employee</h6></button>
                    </form>
                </td>
                <td>
                    <form action="/updateEmployee.jsp" method="get">

                        <input type="hidden" name="employeeId" value="${employee.employeeId}"/>
                        <input type="hidden" name="firstName" value="${employee.firstName}"/>
                        <input type="hidden" name="lastName" value="${employee.lastName}"/>
                        <input type="hidden" name="email" value="${employee.email}"/>
                        <input type="hidden" name="salary" value="${employee.salary}"/>
                        <input type="hidden" name="hireDate" value="${employee.hireDate}"/>

                        <button class="btn btn-light" name="departmentId" type="submit"
                                value="${param.departmentId}"><h6>Update Employee</h6></button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div class="row justify-content-md-around">

        <form action = <c:url value= "/createEmployee.jsp"/> method="get">
            <button class="btn btn-success" name="departmentId" type="submit" value="${departmentId}">
                <h6>Create Employee</h6></button>
        </form>

        <div>
            <a class="btn btn-success" href="/departmentList.do" role="button">Main Page</a>
        </div>
    </div>

</div>

</body>
</html>
