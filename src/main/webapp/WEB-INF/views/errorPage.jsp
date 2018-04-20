
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<html>
<head>
    <title>ErrorPage</title>
</head>
<body>
<div class="container">
    <h1> Error Page</h1>
    <h3>${pageContext.errorData.throwable.message}</h3>
    <a class="btn btn-primary" href="/departmentList.do" role="button">Main page</a>
</div>
</body>
</html>
