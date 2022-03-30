<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>404</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<p style="text-align: center">
    <img src="${pageContext.request.contextPath}/img/404-error.png" alt="404" width="250px"/>
</p>
<h3>Not found</h3>
<h4>The requested url was not found on server</h4>
</body>
</html>