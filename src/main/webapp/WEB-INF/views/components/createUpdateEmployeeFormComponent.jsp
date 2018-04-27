<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group row">
    <label for="firstName" class="col-2 col-form-label">First Name</label>
    <div class="col-6">
        <input class="form-control" type="text" name="firstName" id="firstName" value="${param.firstName}">
    </div>
    <div>
        <c:out value="${violationMap.firstName}"></c:out>
    </div>
    <c:out value="${departmentId}"></c:out>
    <c:out value="${param.departmentIdQuery}"></c:out>


</div>
<div class="form-group row">
    <label for="lastName" class="col-2 col-form-label">Last Name</label>
    <div class="col-6">
        <input class="form-control" type="text" name="lastName" id="lastName" value="${param.lastName}">
    </div>
    <div>
        <c:out value="${violationMap.lastName}"></c:out>
    </div>
</div>

<div class="form-group row">
    <label for="email" class="col-2 col-form-label">Email</label>
    <div class="col-6">
        <input class="form-control" type="text" name="email" id="email" value="${param.email}">
    </div>
    <div>
        <c:out value="${violationMap.email}"></c:out>
    </div>
</div>

<div class="form-group row">
    <label for="salary" class="col-2 col-form-label">Salary</label>
    <div class="col-6">
        <input class="form-control" type="text" name="salary" id="salary" value="${param.salary}">
    </div>
    <div>
        <c:out value="${violationMap.salary}"></c:out>
    </div>
</div>

<div class="form-group row">
    <label for="hireDate" class="col-2 col-form-label">Hire Date</label>
    <div class="col-6">
        <input class="form-control" type="date" name="hireDate" id="hireDate" value="${param.hireDate}">
    </div>
    <div>
        <c:out value="${violationMap.hireDate}"></c:out>
    </div>
</div>

<input type="hidden" name="employeeId" value="${param.employeeId}"/>
<input type="hidden" name="departmentId" value="${param.departmentId}"/>
<input type="hidden" name="query" value="${param.departmentIdQuery}"/>

<div class="row justify-content-md-between">
    <button type="submit" class="btn btn-primary">Submit</button>
    <a class="btn btn-success" href= <c:url value =  "/getDepartmentsEmployees.do?departmentId=${param.departmentId}"/>   role="button">Employee List</a>
</div>
