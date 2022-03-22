<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Value" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>

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
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_ORDERS.path}">| Список заказов |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">| Выйти |</a>
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
            <a href="${pageContext.request.contextPath}${AdminPath.BOOK_INFO.path}?${Parameter.BOOK_ID}=${order.bookDto.id}">
                ${order.bookDto.id} </a>
        </td>
    </tr>
    <tr>
        <th>Код пользователя</th>
        <td>
            <a href="${pageContext.request.contextPath}${AdminPath.READER_INFO.path}?${Parameter.READER_ID}=${order.userDto.id}">
                ${order.userDto.id} </a>
        </td>
    </tr>
    <tr>
        <th>Статус резерва</th>
        <td>${order.reserveStatus.title}</td>
    </tr>
    <tr>
        <th>Статус одобрения</th>
        <td>
            <c:choose>
                <c:when test="${order.approved}">
                    <p style="color: green"><b>ОДОБРЕН</b></p>
                    <c:if test="${order.reserveStatus eq 'REFUND'}">
                        <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                            <input type="hidden" name="action" value="cancel">
                            <input type="submit" value="Закрыть">
                        </form>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <p style="color:red"><b>НЕОДОБРЕН</b></p>
                    <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                        <input type="hidden" name="${Parameter.ACTION}" value="${Value.APPROVE}">
                        <input type="submit" value="Одобрить">
                    </form>
                    <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                        <input type="hidden" name="${Parameter.ACTION}" value="${Value.CANCEL}">
                        <input type="submit" value="Отменить">
                    </form>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</body>
</html>