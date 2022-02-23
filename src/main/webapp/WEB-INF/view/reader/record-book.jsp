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
<c:if test="${recordBook == null}">
    <c:redirect url="/reader/book-search">
        <c:param name="found" value="0"/>
    </c:redirect>
</c:if>
<h2>Выберите книгу из списка</h2>
<h3>Для оформления заказа нажмите на код понравившейся книги</h3>
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
    <c:forEach var="catalog" items="${recordBook}">
        <tr>
            <td>
                <form action="<%= request.getContextPath() %>/reader/order" method="post">
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
<h3 style="text-align: right;"><a href="<%= request.getContextPath() %>/reader/book-search"> Назад </a></h3>
</body>
</html>