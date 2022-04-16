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
        <%@include file='/WEB-INF/css/book-search.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
    <script type="text/javascript" language="javascript">
        <%@include file='/WEB-INF/script/submit-disabled.js' %>
    </script>
</head>
<body class="block">
<div align="right">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${UserPath.READER.path}">
                | <fmt:message key="reader.link-account"/> |
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">
                | <fmt:message key="reader.link-exit"/> |
            </a>
        </li>
    </ul>
</div>
<h2><fmt:message key="reader.header-book-search"/></h2>
<table>
    <form name="form" action="${pageContext.request.contextPath}${UserPath.ORDER.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-id"/></th>
            <td>
                <input id="bookId" type="number" name="${RequestParameter.BOOK_ID}" onKeyup="checkSearchParam(this)"
                       placeholder="<fmt:message key="reader.search-by-id-placeholder"/>" required>
            </td>
            <td  style="with: 45%;">
                <input id="submit" class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form name="form" action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-title"/></th>
            <td>
                <input id="bookTitle" type="text" name="${RequestParameter.BOOK_TITLE}" onKeyup="checkform()"
                       placeholder="<fmt:message key="reader.search-by-title-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input id="submit" class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form name="form" action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-authors"/></th>
            <td><input id="authorName" type="text" name="${RequestParameter.AUTHOR_NAME}" onKeyup="checkform()"
                       placeholder="<fmt:message key="reader.search-by-authors-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input id="submit" class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
            </td>
        </tr>
    </form>

    <form name="form" action="${pageContext.request.contextPath}${UserPath.CATALOG.path}" method="get">
        <tr>
            <th><fmt:message key="reader.search-by-category"/></th>
            <td>
                <input id="categoryName" type="text" name="${RequestParameter.CATEGORY_NAME}" onKeyup="checkform()"
                       placeholder="<fmt:message key="reader.search-by-category-placeholder"/>" required>
            </td>
            <td>
                <input type="hidden" name="${RequestParameter.PAGE}" value="1">
                <input id="submit" class="submit" type="submit" value="<fmt:message key="reader.button-search"/>">
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