<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>500</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<p style="text-align: center">
    <img src="${pageContext.request.contextPath}/img/500-error.png" alt="500" width="250px"/>
</p>
<h3>Internal Server Error</h3>
<h4>Server encountered an unexpected condition that prevented it from fulfilling the request</h4>
</body>
</html>