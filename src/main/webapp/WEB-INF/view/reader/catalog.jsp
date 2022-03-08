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
    <c:forEach var="catalog" items="${catalog}">
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/reader/order" method="post">
                    <input style="font-size: 15px; " type="submit" name="bookId" value="${catalog.id}">
                </form>
            </td>
            <td>${catalog.title}</td>
            <td>
                <c:forEach var="author" items="${catalog.authors}">
                    ${author.firstName} ${author.lastName}<br>
                </c:forEach>
            </td>
            <td>${catalog.category.name}</td>
            <td id="column">${catalog.number}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h4>Сраница ${param.page}</h4>
<nav style="font-size: 20px; text-align: center;">
    <c:forEach var="page" items="${pages}">
        <a href="${sessionScope.url}&page=${page}">| - ${page} - |</a>
    </c:forEach>
</nav>
<h3 style="text-align: right;"><a href="${pageContext.request.contextPath}/reader/book-search"> Поиск книг </a></h3>
</body>
</html>