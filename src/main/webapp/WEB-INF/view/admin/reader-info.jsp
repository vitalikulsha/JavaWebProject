<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-reader"/></title>
    <style>
        <%@include file='/WEB-INF/css/order-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_READERS.path}">
        | <fmt:message key="admin.link-reader-list"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">
        | <fmt:message key="admin.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
        | <fmt:message key="admin.link-exit"/> |
    </a>
</h4>
<h2><fmt:message key="admin.header-reader"/></h2>
<c:set var="user" scope="request" value="${reader}"/>
<table>
    <tr>
        <th><fmt:message key="admin.reader-id"/></th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.reader-first-name"/></th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.reader-last-name"/></th>
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
<h3 style="text-align: center;"><fmt:message key="admin.reader-order-list"/></h3>
<c:if test="${empty readerOrders}">
    <h4 style="text-align: center;"><fmt:message key="admin.reader-order-list-empty"/></h4>
</c:if>
<c:if test="${not empty readerOrders}">
<table>
    <tr>
        <th><fmt:message key="order.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="order.reserve"/></th>
        <th><fmt:message key="order.approval"/></th>
    </tr>
    <c:forEach var="order" items="${readerOrders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}?${RequestParameter.ORDER_ID}=${order.id}">
                    ${order.id} </a>
            </td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus.title}</td>
            <td>
                <c:choose>
                    <c:when test="${order.approved}">
                        <p style="color: green"><b><fmt:message key="order.approved"/></b></p>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red"><b><fmt:message key="order.not-approved"/></b></p>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>
</body>
</html>