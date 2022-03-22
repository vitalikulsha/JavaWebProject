<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Value" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>

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
    <a href="${pageContext.request.contextPath}${UserPath.READER.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">| Выйти |</a>
</h4>
<c:set var="order" scope="request" value="${order}"/>
<h2>Информация о заказе</h2>
<table style="with: 900px; margin: auto;">
    <tr>
        <th>Код заказа</th>
        <td>[${order.id}]</td>
    </tr>
    <tr>
        <th>Название книги</th>
        <td>[${order.bookDto.id}] - ${order.bookDto.title}</td>
    </tr>
    <tr>
        <th>Авторы</th>
        <td>
            <c:forEach var="author" items="${order.bookDto.authors}">
                [${author.id}] - ${author.firstName} ${author.lastName}<br>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Год издания</th>
        <td>${order.bookDto.publicationYear} г.</td>
    </tr>
    <tr>
        <th>Количество страниц</th>
        <td>${order.bookDto.numberPages} стр.</td>
    </tr>
    <tr>
        <th>Категория</th>
        <td>[${order.bookDto.category.id}] - ${order.bookDto.category.name}</td>
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
                </c:when>
                <c:otherwise>
                    <p style="color:red"><b>НЕОДОБРЕН</b></p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
<h3 style="text-align: center;">
    <a href="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}">| Список заказов |</a>
    <a href="${pageContext.request.contextPath}${UserPath.BOOK_SEARCH.path}">| Поиск книг |</a>
</h3>
</body>
</html>