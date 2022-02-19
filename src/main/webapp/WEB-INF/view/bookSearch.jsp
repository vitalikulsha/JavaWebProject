<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Поиск книги</title>
    <style>
        <%@include file='/WEB-INF/css/bookSearchStyle.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4> Добро пожаловать!</h4>
<h2>Поиск книг</h2>
<table>
    <form action="/" method="POST" id="book-title-form">
        <tr>
            <th>Найти книги по названию:</th>
            <td>
                <input class="entry-field" type="text" name="bookTitle"
                       placeholder="Введите название книги поностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" name="submit" id="book-title-submit" value="Найти">
            </td>
        </tr>
    </form>
    <form action="/" method="POST" id="author-name-form">
        <tr>
            <th>Найти книги по автору:</th>
            <td><input class="entry-field" type="password" name="authorName"
                       placeholder="Введите имя или фамилию автора полностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" name="submit" id="author-name-submit" value="Найти">
            </td>
        </tr>
    </form>
    <form action="/" method="POST" id="category-name-form">
        <tr>
            <th>Найти книги по категории:</th>
            <td>
                <input class="entry-field" type="text" name="categoryName"
                       placeholder="Введиет название категории полностью или частично" required>
            </td>
            <td>
                <input class="submit" type="submit" name="submit" id="category-name-submit" value="Найти">
            </td>
        </tr>
    </form>
    <form action="<%= request.getContextPath() %>/book-catalog" method="get" id="all-book-form">
        <tr>
            <th>Получить весь список книг:</th>
            <td></td>
            <td>
                <input class="submit" type="submit" name="allBooks" id="all-books-submit" value="Получить">
            </td>
        </tr>
    </form>
</table>
</body>
</html>