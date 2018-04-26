<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Create Employee</title>
</head>
<body>

<div class="container">

    <h2>Create Employee Form</h2>

    <form action="/createEmployee.do" method="post">
        <jsp:include page="/createUpdateEmployeeFormComponent.jsp"></jsp:include>
    </form>

</div>

</body>
</html>
