<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>403</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<p style="text-align: center">
    <img src="${pageContext.request.contextPath}/img/403-error.png" alt="403" width="250px"/>
</p>
<h3>Forbidden</h3>
<h4>Not enough rights to access the requested resource. The server understood the request, but will not fulfill it</h4>
</body>
</html>