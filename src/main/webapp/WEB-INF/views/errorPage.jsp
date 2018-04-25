<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<html>
<head>
    <title>ErrorPage</title>
</head>
<body>
<div class="container">
    <div class="jumbotron" style="padding: 180px">
        <h1 class="text-center">Error page<p></p>
            ${param}
            <c:out value="${pageContext.errorData}"></c:out>
            <p>
                <small class="text-center"><c:out value="${pageContext.errorData.throwable.message}"></c:out>
                </small>
            </p>
        </h1>
        <p class="text-center">Try pressing the back button or clicking on this button.</p>
        <p class="text-center"><a class="btn btn-primary" href="/departmentList.do"><i
                class="fa fa-home"></i>MainPage</a></p>
    </div>

</div>
</body>
</html>
