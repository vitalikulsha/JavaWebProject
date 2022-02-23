<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Поиск книги</title>
    <style>
        <%@include file='/WEB-INF/css/book-search-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;"><a href="<%= request.getContextPath() %>/logout"> Выйти </a></h4>
<h4 style="text-align: right;">
    Добро пожаловать, ${sessionScope.user.userName}! Доступ с правами ${sessionScope.user.role}.
</h4>
<h2>Поиск книг</h2>
<c:if test="${param.found eq 0}">
    <h3 class="error">Ничего не найдено, повторите поиск.</h3>
</c:if>
<table>
    <form action="<%= request.getContextPath() %>/reader/order" method="post">
        <tr>
            <th>Найти книги по коду:</th>
            <td>
                <input class="entry-field" type="text" name="bookId"
                       placeholder="Введите код книги" required>
            </td>
            <td>
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="<%= request.getContextPath() %>/reader/record-book" method="post">
        <tr>
            <th>Найти книги по названию:</th>
            <td>
                <input class="entry-field" type="text" name="bookTitle"
                       placeholder="Введите название книги поностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="<%= request.getContextPath() %>/reader/record-book" method="post">
        <tr>
            <th>Найти книги по автору:</th>
            <td><input class="entry-field" type="text" name="authorName"
                       placeholder="Введите имя или фамилию автора полностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="<%= request.getContextPath() %>/reader/record-book" method="post">
        <tr>
            <th>Найти книги по категории:</th>
            <td>
                <input class="entry-field" type="text" name="categoryName"
                       placeholder="Введиет название категории полностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="<%= request.getContextPath() %>/reader/record-book" method="get">
        <tr>
            <th>Получить весь список книг:</th>
            <td></td>
            <td>
                <input class="submit" type="submit" name="allBooks" value="Получить">
            </td>
        </tr>
    </form>
</table>
</body>
</html>