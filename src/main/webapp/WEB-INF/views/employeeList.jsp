<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title> Emploee List</title>
</head>
<body>
<h1 class="d-flex justify-content-center"> <c:out value="${department.departmentName}"></c:out> Employee List </h1>
<div class="container">
    <div class="row justify-content-md-center">
        <c:set var="departmentIdQuery" scope="session" value=" ${requestScope['javax.servlet.forward.query_string']}"></c:set>
        <ul class="list-group">
            <c:forEach var="employee" items="${employeeList}">
                <li class="list-group-item">
                    <div class="d-flex justify-content-between">
                        <h5> Id:<br> <c:out value="${employee.employeeId}"></c:out></h5>
                        <h5>First Name: <br><c:out value="${employee.firstName}"></c:out></h5>
                        <h5>Last Name: <br><c:out value="${employee.lastName}"></c:out></h5>
                        <h5>Email:<br> <c:out value="${employee.email}"></c:out></h5>
                        <h5>Salary: <br><c:out value="${employee.salary}"></c:out></h5>
                        <h5>Hire Date:  <br><c:out value="${employee.hireDate}"></c:out></h5>
                        <form action="/deleteEmployee.do" method="post">
                            <button class="btn btn-primary" name="employeeId" type="submit"
                                    value="${employee.employeeId}"><h6>Delete Employee</h6></button>
                        </form>

                        <form action="/updateEmployee.jsp" method="get">

                            <input type="hidden" name="employeeId" value="${employee.employeeId}"/>
                            <input type="hidden" name="firstName" value="${employee.firstName}"/>
                            <input type="hidden" name="lastName" value="${employee.lastName}"/>
                            <input type="hidden" name="email" value="${employee.email}"/>
                            <input type="hidden" name="salary" value="${employee.salary}"/>
                            <input type="hidden" name="hireDate" value="${employee.hireDate}"/>

                            <button class="btn btn-primary" name="departmentId" type="submit"
                                    value="${department.departmentId}"><h6>Update Employee</h6></button>
                        </form>
                    </div>
                </li>

            </c:forEach>
        </ul>
    </div>
    <div class="row justify-content-md-between">

        <form action="/createEmployee.jsp" method="get">
<%--
            <button class="btn btn-primary" name="departmentId" type="submit" value="${department.departmentId}"><h6>
--%>
                <button class="btn btn-primary" name="departmentId" type="submit" value="${departmentId}"><h6>

                Create Employee</h6></button>
        </form>

        <div>
            <a class="btn btn-primary" href="/departmentList.do" role="button">Main Page</a>
        </div>
    </div>

</div>

</body>
</html>
