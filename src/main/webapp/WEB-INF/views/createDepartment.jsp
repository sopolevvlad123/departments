<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Create Department</title>
</head>
<body>
<div class="container">
    <div class="row d-flex justify-content-center">
        <h2>Create Department Form</h2>
    </div>
    <form action="/createDepartment.do" method="post">
        <jsp:include page="/createUpdateDepartmentFormComponent.jsp"></jsp:include>
    </form>
</div>
</body>
</html>
