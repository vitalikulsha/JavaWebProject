<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-place-order"/></title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.BOOK_SEARCH.path}">
        | <fmt:message key="reader.link-book-search"/> |
    </a>
    <a href="${pageContext.request.contextPath}${UserPath.READER.path}">
        | <fmt:message key="reader.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">
        | <fmt:message key="reader.link-exit"/> |
    </a>
</h4>
<h2><fmt:message key="reader.header-place-order"/></h2>
<c:set var="book" scope="request" value="${book}"/>
<form id="order" action="${pageContext.request.contextPath}${UserPath.ORDER.path}" method="post">
</form>
<table>
    <tr>
        <th><fmt:message key="book.id"/></th>
        <td>${book.id}</td>
    </tr>
    <tr>
        <th><fmt:message key="book.title"/></th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th><fmt:message key="book.authors"/></th>
        <td>
            <c:forEach var="author" items="${book.authors}">
                ${author.firstName} ${author.lastName}<br>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th><fmt:message key="book.publication-year"/></th>
        <td>${book.publicationYear}</td>
    </tr>
    <tr>
        <th><fmt:message key="book.number-pages"/></th>
        <td>${book.numberPages}</td>
    </tr>
    <tr>
        <th><fmt:message key="book.category"/></th>
        <td>${book.category.name}</td>
    </tr>
    <tr>
        <th><fmt:message key="order.reserve"/></th>
        <td>
            <select style="font-size: 15px" name="${RequestParameter.RESERVE_STATUS}" form="order">
                <option value="${ReserveStatus.READING_ROOM}">${ReserveStatus.READING_ROOM.title}</option>
                <option value="${ReserveStatus.LOANED}">${ReserveStatus.LOANED.title}</option>
            </select>
        </td>
    </tr>
</table>
<input type="submit" form="order" value="<fmt:message key="reader.button-reserve"/>">
</body>
</html>