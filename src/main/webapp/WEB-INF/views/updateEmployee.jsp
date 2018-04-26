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
        <jsp:include page="/createUpdateEmployeeFormComponent.jsp"></jsp:include>
    </form>
</div>

</body>
</html>
