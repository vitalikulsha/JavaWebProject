<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.path.UserPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-catalog"/></title>
    <style>
          <%@include file='/WEB-INF/css/book-catalog-style.css' %>
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
<h2><fmt:message key="reader.header-catalog"/></h2>
<h3><fmt:message key="reader.header-click-id"/></h3>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="book.authors"/></th>
        <th><fmt:message key="book.category"/></th>
        <th><fmt:message key="book.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${catalog}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${UserPath.ORDER.path}?${RequestParameter.BOOK_ID}=${book.id}">
                    ${book.id} </a>
            </td>
            <td>${book.title}</td>
            <td>
                <c:forEach var="author" items="${book.authors}">
                    ${author.firstName} ${author.lastName}<br>
                </c:forEach>
            </td>
            <td>${book.category.name}</td>
            <td id="column">${book.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</body>
</html>