<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список заказов</title>
    <style>
        <%@include file='/WEB-INF/css/book-catalog-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/admin"> Личный кабинет</a>
    <a href="${pageContext.request.contextPath}/logout"> Выйти </a>
</h4>
<h2>Список всех заказов читателей</h2>
<c:set var="user" scope="request" value="${user}"/>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th>Код заказа</th>
        <th>Код книги</th>
        <th>Название книги</th>
        <th>Статус резерва</th>
        <th>Статус одобрения</th>
        <th>Код пользователя</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td>${order.id}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/book-info" method="post">
                    <input style="font-size: 15px; " type="submit" name="bookId" value="${order.bookDto.id}">
                </form>
            </td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus}</td>
            <td>${order.approved}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/reader-info" method="post">
                    <input style="font-size: 15px; " type="submit" name="readerId" value="${order.userDto.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:choose>
    <c:when test="${empty param.page}">
        <h4>Страница 1</h4>
    </c:when>
    <c:otherwise>
        <h4>Страница ${param.page}</h4>
    </c:otherwise>
</c:choose>
<nav style="font-size: 20px; text-align: center;">
    <c:forEach var="page" items="${pages}">
        <a href="${sessionScope.url}&page=${page}">| - ${page} - |</a>
    </c:forEach>
</nav>

</body>
</html>