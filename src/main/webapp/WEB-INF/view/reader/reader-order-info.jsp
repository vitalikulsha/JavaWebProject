<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="reader.title-order-info"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<nav class="menu">
    <ul class="nav nav-link">
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${UserPath.READER.path}" class="link">
                | <fmt:message key="reader.link-account"/> |
            </a>
        </li>
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}" class="link">
                | <fmt:message key="reader.link-exit"/> |
            </a>
        </li>
    </ul>
</nav>
<c:set var="order" scope="request" value="${order}"/>
<table  style="width: 700px;">
    <caption>
        <h2><fmt:message key="reader.header-order-info"/></h2>
    </caption>
    <tr>
        <th class="th-order"><fmt:message key="order.id"/></th>
        <td>[${order.id}]</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.title"/></th>
        <td>[${order.bookDto.id}] - ${order.bookDto.title}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.authors"/></th>
        <td>
            <c:forEach var="author" items="${order.bookDto.authors}">
                [${author.id}] - ${author.firstName} ${author.lastName}<br>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.publication-year"/></th>
        <td>${order.bookDto.publicationYear} г.</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.number-pages"/></th>
        <td>${order.bookDto.numberPages} стр.</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="book.category"/></th>
        <td>[${order.bookDto.category.id}] - ${order.bookDto.category.name}</td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="order.reserve"/></th>
        <td>
            <c:choose>
                <c:when test="${order.reserveStatus eq 'REFUND'}">
                    <fmt:message key="order.refund"/>
                </c:when>
                <c:when test="${order.reserveStatus eq 'LOANED'}">
                    <fmt:message key="order.loaned"/>
                </c:when>
                <c:when test="${order.reserveStatus eq 'READING_ROOM'}">
                    <fmt:message key="order.reading-room"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th class="th-order"><fmt:message key="order.approval"/></th>
        <td>
            <c:choose>
                <c:when test="${order.approved}">
                    <p style="color: green"><strong><fmt:message key="order.approved"/></strong></p>
                </c:when>
                <c:otherwise>
                    <p style="color:red"><strong><fmt:message key="order.not-approved"/></strong></p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
<h3 style="text-align: center;">
    <a href="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}">
        | <fmt:message key="reader.link-order-list"/> |
    </a>
    <a href="${pageContext.request.contextPath}${UserPath.BOOK_SEARCH.path}">
        | <fmt:message key="reader.link-book-search"/> |
    </a>
</h3>
</body>
</html>