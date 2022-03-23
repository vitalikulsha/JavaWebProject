<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>

<html>
<head>
    <title>Админ</title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">| Выйти |</a></h4>
<h2>Личный кабинет</h2>
<c:set var="user" scope="request" value="${user}"/>
<table>
    <tr>
        <th>Имя пользователя</th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th>Фамилия пользователя</th>
        <td>${user.lastName}</td>
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
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_ORDERS.path}">| Список заказов |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_READERS.path}">| Список читателей |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_BOOKS.path}">| Список книг |</a>
</h3>

</body>
</html>