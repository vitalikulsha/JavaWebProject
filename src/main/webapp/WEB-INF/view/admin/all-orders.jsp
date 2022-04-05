<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.Parameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-orders"/></title>
    <style>
        <%@include file='/WEB-INF/css/book-catalog-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">
        | <fmt:message key="admin.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
        | <fmt:message key="admin.link-exit"/> |
    </a>
</h4>
<h2><fmt:message key="admin.header-readers"/></h2>
<c:if test="${empty allOrders}">
    <h4 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h4>
</c:if>
<c:if test="${not empty allOrders}">
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th><fmt:message key="order.id"/></th>
        <th><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="order.reserve"/></th>
        <th><fmt:message key="order.approval"/></th>
        <th><fmt:message key="book.quantity"/></th>
        <th><fmt:message key="admin.reader-id"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}?${Parameter.ORDER_ID}=${order.id}"> ${order.id} </a>
            </td>
            <td>${order.bookDto.id}</td>
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
            <td>${order.bookDto.quantity}</td>
            <td>${order.userDto.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</c:if>
</body>
</html>