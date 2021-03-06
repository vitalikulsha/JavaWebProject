<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="reader.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<nav class="menu">
    <ul class="nav nav-link">
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}" class="link">
                | <fmt:message key="reader.link-exit"/> |
            </a>
        </li>
        <li class="menu-li">
            <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
        </li>
    </ul>
</nav>
<c:set var="user" scope="request" value="${user}"/>
<table style="width: 550px;">
    <caption>
        <h2><fmt:message key="reader.header"/></h2>
    </caption>
    <tr>
        <th class="th-user"><fmt:message key="reader.first-name"/></th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="reader.last-name"/></th>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="reader.id"/></th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="reader.phone-number"/></th>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="reader.email"/></th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="reader.access-rights"/></th>
        <td>${user.role}</td>
    </tr>
</table>
<ul class="nav nav-page" style="width: 560px;">
    <li class="menu-li">
        <a href="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}" class="link">
            | <fmt:message key="reader.link-order-list"/> |
        </a>
    </li>
    <li class="menu-li">
        <a href="${pageContext.request.contextPath}${UserPath.BOOK_SEARCH.path}" class="link">
            | <fmt:message key="reader.link-book-search"/> |
        </a>
    </li>
    <li class="menu-li">
        <a href="${pageContext.request.contextPath}${UserPath.EDIT.path}" class="link">
            | <fmt:message key="reader.link-edit-profile"/> |
        </a>
    </li>
</ul>
</body>
</html>