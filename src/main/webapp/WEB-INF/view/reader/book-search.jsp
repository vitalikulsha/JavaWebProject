<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>

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
    <form action="${pageContext.request.contextPath}/reader/order" method="get">
        <tr>
            <th>Найти книги по коду:</th>
            <td>
                <input class="entry-field" type="number" name="${Parameter.BOOK_ID}"
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
                <input class="entry-field" type="text" name="${Parameter.BOOK_TITLE}"
                       placeholder="Введите название книги поностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="${Parameter.PAGE}" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Найти книги по автору:</th>
            <td><input class="entry-field" type="text" name="${Parameter.AUTHOR_NAME}"
                       placeholder="Введите фамилию автора полностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="${Parameter.PAGE}" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Найти книги по категории:</th>
            <td>
                <input class="entry-field" type="text" name="${Parameter.CATEGORY_NAME}"
                       placeholder="Введиет название категории полностью или частично" required>
            </td>
            <td>
                <input type="hidden" name="${Parameter.PAGE}" value="1">
                <input class="submit" type="submit" value="Найти">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}/reader/catalog" method="get">
        <tr>
            <th>Получить весь список книг:</th>
            <td></td>
            <td>
                <input type="hidden" name="${Parameter.PAGE}" value="1">
                <input class="submit" type="submit" value="Получить">
            </td>
        </tr>
    </form>
</table>
<c:if test="${isExists}">
    <h3 class="error">Книга с id=${book.id} уже есть в списке заказов. Выберите другую книгу.</h3>
</c:if>
<c:if test="${not empty bookFound}">
    <c:if test="${!bookFound}">
        <h3 class="error">Ничего не найдено. Повторите попытку</h3>
    </c:if>
</c:if>
</body>
</html>