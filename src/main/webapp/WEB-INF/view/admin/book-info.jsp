<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="admin.title-book"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<div style="float: right;">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.ALL_BOOKS.path}">
                | <fmt:message key="admin.link-book-list"/> |
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">
                | <fmt:message key="admin.link-account"/> |
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
                | <fmt:message key="admin.link-exit"/> |
            </a>
        </li>
    </ul>
</div>
<c:set var="book" scope="request" value="${book}"/>
<table style="width: 700px;">
    <caption>
        <h2><fmt:message key="admin.header-book"/></h2>
    </caption>
    <tr>
        <th class="th-order"><fmt:message key="book.id"/></th>
        <td>${book.id}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.title"/></th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.authors"/></th>
        <td>
            <c:forEach var="author" items="${book.authors}">
                [${author.id}] - ${author.firstName} ${author.lastName}<br>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.publication-year"/></th>
        <td>${book.publicationYear}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.number-pages"/></th>
        <td>${book.numberPages}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.category"/></th>
        <td>[${book.category.id}] - ${book.category.name}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.quantity"/></th>
        <td>${book.quantity}</td>
    </tr>
</table>
</body>
</html>