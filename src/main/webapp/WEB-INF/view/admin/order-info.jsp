<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Информация о заказе</title>
    <style>
        <%@include file='/WEB-INF/css/book-catalog-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/admin">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}/logout">| Выйти |</a>
</h4>
<c:set var="order" scope="request" value="${order}"/>
<h2>Информация о заказе</h2>
<table style="with: 900px; margin: auto;">
    <tr>
        <th>Код заказа</th>
        <td>${order.id}</td>
    </tr>
    <tr>
        <th>Код книги</th>
        <td>
            <a href="${pageContext.request.contextPath}/admin/book-info?bookId=${order.booDto.id}"> ${order.booDto.id} </a>
        </td>
    </tr>
    <tr>
        <th>Код пользователя</th>
        <td>
            <a href="${pageContext.request.contextPath}/admin/book-info?bookId=${order.userDto.id}"> ${order.userDto.id} </a>
        </td>
    </tr>
    <tr>
        <th>Статус резерва</th>
        <td>${order.reserveStatus.title}</td>
    </tr>
    <tr>
        <th>Статус одобрения</th>
        <td>${order.approved}</td>
    </tr>
</table>

<jsp:include page="/WEB-INF/view/template/pagination.jsp" />

</body>
</html>