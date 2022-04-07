<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-book-search"/></title>
    <style>
        <%@include file='/WEB-INF/css/book-search-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.READER.path}">
        | <fmt:message key="reader.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">
        | <fmt:message key="reader.link-exit"/> |
    </a>
</h4>
<h2><fmt:message key="reader.header-book-search"/></h2>
<table>
    <form action="${pageContext.request.contextPath}${UserPath.ORDER.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-id"/></th>
            <td>
                <input class="entry-field" type="number" name="${RequestParameter.BOOK_ID}"
                       placeholder="<fmt:message key="reader.search-by-id-placeholder"/>" required>
            </td>
            <td>
                <input class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-title"/></th>
            <td>
                <input class="entry-field" type="text" name="${RequestParameter.BOOK_TITLE}"
                       placeholder="<fmt:message key="reader.search-by-title-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-authors"/></th>
            <td><input class="entry-field" type="text" name="${RequestParameter.AUTHOR_NAME}"
                       placeholder="<fmt:message key="reader.search-by-authors-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-category"/></th>
            <td>
                <input class="entry-field" type="text" name="${RequestParameter.CATEGORY_NAME}"
                       placeholder="<fmt:message key="reader.search-by-category-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.get-all"/></th>
            <td></td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input class="submit" type="submit" value="<fmt:message key="reader.button-get"/>">
            </td>
        </tr>
    </form>
</table>
<c:if test="${not empty bookExists && bookExists}">
    <h3 class="error"><fmt:message key="reader.book-exists"/></h3>
</c:if>
<c:if test="${not empty bookFound && !bookFound}">
    <h3 class="error"><fmt:message key="reader.book-not-found"/></h3>
</c:if>
</body>
</html>