<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>

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
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">| Выйти |</a>
</h4>
<h2>Список всех заказов читателей</h2>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th>Код заказа</th>
        <th>Код книги</th>
        <th>Название книги</th>
        <th>Статус резерва</th>
        <th>Статус одобрения</th>
        <th>Количество книг в библиотеке</th>
        <th>Код пользователя</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}?${Parameter.ORDER_ID}=${order.id}"> ${order.id} </a>
            </td>
            <td>${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus.title}</td>
            <td>
                <c:choose>
                    <c:when test="${order.approved}"> <p style="color: green"><b>ОДОБРЕН</b></p> </c:when>
                    <c:otherwise> <p style="color:red"><b>НЕОДОБРЕН</b></p> </c:otherwise>
                </c:choose>
            </td>
            <td>${order.bookDto.number}</td>
            <td>${order.userDto.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/view/template/pagination.jsp" />

</body>
</html>