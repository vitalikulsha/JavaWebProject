<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="admin.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<div style="float: right;">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
                | <fmt:message key="admin.link-exit"/> |
            </a>
        </li>
        <li>
            <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
        </li>
    </ul>
</div>
<c:set var="user" scope="request" value="${user}"/>
<table style="width: 500px;">
    <caption>
        <h2><fmt:message key="admin.header"/></h2>
    </caption>
    <tr>
        <th class="th-user"><fmt:message key="admin.first-name"/></th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="admin.last-name"/></th>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="admin.phone-number"/></th>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="admin.email"/></th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th class="th-user"><fmt:message key="admin.access-rights"/></th>
        <td>${user.role}</td>
    </tr>
</table>
<ul class="nav nav-page" style="width: 500px;" >
    <li>
        <a href="${pageContext.request.contextPath}${AdminPath.ALL_ORDERS.path}">
            | <fmt:message key="admin.link-order-list"/> |
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}${AdminPath.ALL_READERS.path}">
            | <fmt:message key="admin.link-reader-list"/> |
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}${AdminPath.ALL_BOOKS.path}">
            | <fmt:message key="admin.link-book-list"/> |
        </a>
    </li>
</ul>
</body>
</html>