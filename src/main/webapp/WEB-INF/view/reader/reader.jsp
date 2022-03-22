<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Оформление заказа</title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>


    </style>
</head>
<body class="block">
<h4 style="text-align: right;"><a href="${pageContext.request.contextPath}/logout"> Выйти </a></h4>
<h2>Личный кабинет</h2>
<c:set var="user" scope="request" value="${user}"/>
<table>
    <tr>
        <th>Имя пользователя</th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th>Код пользователя</th>
        <td>${user.id}</td>
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
    <a href="${pageContext.request.contextPath}/reader/reader-orders">| Список заказов |</a>
    <a href="${pageContext.request.contextPath}/reader/book-search">| Поиск книг |</a>
    <a href="${pageContext.request.contextPath}/reader/edit">| Редактировать профиль |</a>
</h3>

</body>
</html>