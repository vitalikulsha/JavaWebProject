<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Оформление заказа</title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Оформление заказа</h2>
<c:set var="book" scope="request" value="${book}"/>
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
        <td>${book.yearIssue}</td>
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
        <th>Место чтения</th>
        <td>
            <select style="font-size: 15px">
                <option>Читальный зал</option>
                <option>Абонемент</option>
            </select>
        </td>
    </tr>
</table>
<h3 style="text-align: center;"><a  href="<%= request.getContextPath() %>/reader/book-search"> Поиск книг </a></h3>
</body>
</html>