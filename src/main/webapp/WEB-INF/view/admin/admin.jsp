<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Админ</title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h2>Страница администратора</h2>
<h4 style="text-align: right;">
    Добро пожаловать, ${sessionScope.user.userName}! Доступ с правами ${sessionScope.user.role}.
    <a href="${pageContext.request.contextPath}/logout"> Выйти </a></h4>
<h2>Личный кабинет</h2>
<c:set var="user" scope="request" value="${user}"/>
<table>
    <tr>
        <th>Имя пользователя</th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th>Номер телефона</th>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <th>E-mail</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th>Права доступа</th>
        <td>${user.role}</td>
    </tr>
</table>

<h3 style="text-align: center;">
    <a href="${pageContext.request.contextPath}/admin/all-orders"> Список всех заказов </a>
</h3>

</body>
</html>