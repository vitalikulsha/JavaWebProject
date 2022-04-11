<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-catalog"/></title>
    <style>
          <%@include file='/WEB-INF/css/user.css' %>
          <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<div align="right">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${UserPath.BOOK_SEARCH.path}">
                | <fmt:message key="reader.link-book-search"/> |
            </a>
        </li>
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
<h2><fmt:message key="reader.header-catalog"/></h2>
<h3><fmt:message key="reader.header-click-id"/></h3>
<table style="width: 980px;">
    <thead>
    <tr>
        <th style="width: 10%;"><fmt:message key="book.id"/></th>
        <th style="width: 35%;"><fmt:message key="book.title"/></th>
        <th style="width: 20%;"><fmt:message key="book.authors"/></th>
        <th style="width: 25%;"><fmt:message key="book.category"/></th>
        <th><fmt:message key="book.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${catalog}">
        <tr>
            <td class="td-center">
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
            <td class="td-center">${book.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</body>
</html>