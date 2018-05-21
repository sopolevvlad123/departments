<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Create Department</title>
</head>
<body>
<div class="container">
    <div class="row d-flex justify-content-center">
        <h2>Save Department Form</h2>
    </div>

    <table>

        <%--@elvariable id="department" type="com.bean.Department"--%>
        <form:form method="POST" action="/saveDepartment.do" modelAttribute="department">
        <div class="form-group row">
            <label for="departmentName" class="col-2 col-form-label">Department Name</label>
            <div class="col-6">
                <form:input path="departmentName"/>
                <form:hidden path="departmentId" value="${department.departmentId}"/>
            </div>
            <div>
                    <%--@elvariable id="violationMap" type="java.util.HashMap"--%>
                <c:out value="${violationMap.departmentName}"/>
            </div>
        </div>
        <div class="row justify-content-md-between">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a class="btn btn-success" href=
                    "<c:url value="/departmentList.do"/>" role="button">Main Page</a>
        </div>
        </form:form>
</div>
</body>
</html>
