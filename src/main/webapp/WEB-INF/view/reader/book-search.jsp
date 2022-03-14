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
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}/reader"> Личный кабинет</a>
    <a href="${pageContext.request.contextPath}/logout">| Выйти |</a>
</h4>
<h2>Поиск книг</h2>
<table>
    <form action="${pageContext.request.contextPath}/reader/order" method="post">
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

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Найти книги по названию:</th>
            <td>
                <input class="entry-field" type="text" name="bookTitle"
                       placeholder="Введите название книги поностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="page" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Найти книги по автору:</th>
            <td><input class="entry-field" type="text" name="authorName"
                       placeholder="Введите фамилию автора полностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="page" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Найти книги по категории:</th>
            <td>
                <input class="entry-field" type="text" name="categoryName"
                       placeholder="Введиет название категории полностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="page" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Получить весь список книг:</th>
            <td></td>
            <td>
                <input type="hidden" name="page" value="1">
                <input class="submit" type="submit" value="Получить">
            </td>
        </tr>
    </form>
</table>
</body>
</html>