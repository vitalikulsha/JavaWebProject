<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus" %>

<html>
<head>
    <title>Оформление заказа</title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/reader/book-search">| Поиск книг |</a>
    <a href="${pageContext.request.contextPath}/reader">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}/logout">| Выйти |</a>
</h4>
<h2>Оформление заказа</h2>
<c:set var="book" scope="request" value="${book}"/>
<form id="order" action="${pageContext.request.contextPath}/reader/order" method="post">
</form>
<table>
    <tr>
        <th>Код книги</th>
        <td>${book.id}</td>
    </tr>
    <tr>
        <th>Название книги</th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th>Авторы</th>
        <td>
            <c:forEach var="author" items="${book.authors}">
                ${author.firstName} ${author.lastName}<br>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Год издания</th>
        <td>${book.publicationYear}</td>
    </tr>
    <tr>
        <th>Количество страниц</th>
        <td>${book.numberPages}</td>
    </tr>
    <tr>
        <th>Категория</th>
        <td>${book.category.name}</td>
    </tr>
    <tr>
        <th>Зарезервировать</th>
        <td>
            <select style="font-size: 15px" name="${Parameter.RESERVE_STATUS}" form="order">
                <option value="${ReserveStatus.READING_ROOM}">${ReserveStatus.READING_ROOM.title}</option>
                <option value="${ReserveStatus.LOANED}">${ReserveStatus.LOANED.title}</option>
            </select>
        </td>
    </tr>
</table>
<input type="submit" form="order" value="Оформить заказ">
</body>
</html>