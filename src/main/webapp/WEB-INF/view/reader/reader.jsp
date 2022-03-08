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
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/logout"> Выйти </a>
</h4>
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
<table><tr><th style="text-align: center;">Список заказов</th></tr></table>
<table style="with: 900px; margin: auto;">
    <thead>

    <tr>
        <th>Код заказа</th>
        <th>Код книги</th>
        <th>Название книги</th>
        <th>Статус резерва</th>
        <th>Статус одобрения</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${userOrders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus}</td>
            <td>${order.approved}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3 style="text-align: center;"><a href="${pageContext.request.contextPath}/reader/book-search"> Поиск книг </a></h3>
</body>
</html>