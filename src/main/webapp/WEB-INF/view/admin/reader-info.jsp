<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Информация о читателе</title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/admin/all-orders"> Список заказов </a>
    <a href="${pageContext.request.contextPath}/admin"> Личный кабинет</a>
    <a href="${pageContext.request.contextPath}/logout"> Выйти </a>
</h4>
<h2>Информация о читателе</h2>
<c:set var="user" scope="request" value="${reader}"/>
<table>
    <tr>
        <th>Код читателя</th>
        <td>${user.id}</td>
    </tr>
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
</body>
</html>