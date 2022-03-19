<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список заказов</title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>



    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/reader/book-search">| Поиск книг |</a>
    <a href="${pageContext.request.contextPath}/reader">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}/logout">| Выйти |</a>
</h4>
<h2>Список заказов</h2>
<c:set var="user" scope="request" value="${user}"/>

<table>
    <tr>
        <th style="text-align: center;">Список заказов</th>
    </tr>
</table>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th>Код заказа</th>
        <th>Код книги</th>
        <th>Название книги</th>
        <th>Статус резерва</th>
        <th>Статус одобрения</th>
        <th>Управление заказом</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${userOrders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus.title}</td>
            <td>
                <c:choose>
                    <c:when test="${order.approved}"><p style="color: green"><b>ОДОБРЕН</b></p></c:when>
                    <c:otherwise><p style="color:red"><b>НЕОДОБРЕН</b></p></c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${order.approved}">
                    <form action="${pageContext.request.contextPath}/reader/reader-orders" method="post">
                        <input type="hidden" name="orderId" value="${order.id}">
                        <input type="hidden" name="reserveStatus" value="RETURN">
                        <input type="submit" value="Вернуть книгу">
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp"/>
</body>
</html>