<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-group row">
    <label for="departmentName" class="col-2 col-form-label">Department Name</label>
    <div class="col-6">
        <input type="hidden" name="departmentId" value="${param.departmentId}"/>
        <input class="form-control" type="text" name="departmentName" id="departmentName"
               value="${param.departmentName}">
    </div>
    <div>
        <c:out value="${violationMap.departmentName}"></c:out>
    </div>
</div>

<div class="row justify-content-md-between">
    <button type="submit" class="btn btn-primary">Submit</button>
    <a class="btn btn-success" href= <c:url value="/departmentList.do"/> role="button" >Main Page</a>
</div>
