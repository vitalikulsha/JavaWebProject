<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Каталог книг</title>
    <style>
          <%@include file='/WEB-INF/css/book-catalog-style.css' %>
          <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Выберите книгу из списка</h2>
<form action="<%= request.getContextPath() %>/book-catalog" method="post">
    <ul class="entry-block">
        <li>Введите код книги для заказа:</li>
        <li><input class="entry-field" type="text" name="bookId" placeholder="Код книги" required></li>
        <li><input class="submit" type="submit" value="Заказать"/></li>
        <li><a href="<%= request.getContextPath() %>/book-search"> Назад </a></li>
    </ul>
</form>
<h3>Список книг в каталоге:</h3>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th>Код</th>
        <th>Название</th>
        <th>Авторы</th>
        <th>Категория</th>
        <th>Количество</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="catalog" items="${bookCatalog}">
        <tr>
            <td>
                <form action="<%= request.getContextPath() %>/order" method="post">
                    <input style="font-size: 15px;" type="submit" name="bookId" id="book-id" value="${catalog.book.id}">
                </form>
            </td>
            <td>${catalog.book.title}</td>
            <td>
                <c:forEach var="author" items="${catalog.book.authors}">
                    ${author.firstName} ${author.lastName}<br>
                </c:forEach>
            </td>
            <td>${catalog.book.category.name}</td>
            <td id="column">${catalog.number}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>