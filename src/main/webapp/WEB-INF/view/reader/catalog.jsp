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
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/reader/book-search">| Поиск книг |</a>
    <a href="${pageContext.request.contextPath}/reader">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}/logout">| Выйти |</a>
</h4>
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
    <c:forEach var="book" items="${catalog}">
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/reader/order" method="get">
                    <input style="font-size: 15px; " type="submit" name="bookId" value="${book.id}">
                </form>
            </td>
            <td>${book.title}</td>
            <td>
                <c:forEach var="author" items="${book.authors}">
                    ${author.firstName} ${author.lastName}<br>
                </c:forEach>
            </td>
            <td>${book.category.name}</td>
            <td id="column">${book.number}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</body>
</html>