<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
        | <fmt:message key="admin.link-exit"/> |
    </a>
    <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
</h4>
<h2><fmt:message key="admin.header"/></h2>
<c:set var="user" scope="request" value="${user}"/>
<table>
    <tr>
        <th><fmt:message key="admin.first-name"/></th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.last-name"/></th>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.phone-number"/></th>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.email"/></th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.access-rights"/></th>
        <td>${user.role}</td>
    </tr>
</table>

<h3 style="text-align: center;">
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_ORDERS.path}">
        | <fmt:message key="admin.link-order-list"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_READERS.path}">
        | <fmt:message key="admin.link-reader-list"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_BOOKS.path}">
        | <fmt:message key="admin.link-book-list"/> |
    </a>
</h3>

</body>
</html>