<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Админ</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Страница администратора</h2>
<h4 style="text-align: right;"><a href="${pageContext.request.contextPath}/logout"> Выйти </a></h4>
<h4 style="text-align: right;">
    Добро пожаловать, ${sessionScope.user.userName}! Доступ с правами ${sessionScope.user.role}.
</h4>
</body>
</html>