<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>

<html>
<head>
    <title>Информация о книге</title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">| Выйти |</a>
</h4>
<h2>Информация о книге</h2>
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
                [${author.id}] - ${author.firstName} ${author.lastName}<br>
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
        <td>[${book.category.id}] - ${book.category.name}</td>
    </tr>
    <tr>
        <th>Количество книг</th>
        <td>${book.number}</td>
    </tr>
</table>
</body>
</html>