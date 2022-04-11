<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-orders"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<div align="right">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">
                | <fmt:message key="admin.link-account"/> |
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
                | <fmt:message key="admin.link-exit"/> |
            </a>
        </li>
    </ul>
</div>
<h2><fmt:message key="admin.header-readers"/></h2>
<c:if test="${empty allOrders}">
    <h4 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h4>
</c:if>
<c:if test="${not empty allOrders}">
<table style="width: 900px;">
    <thead>
    <tr>
        <th style="width: 10%;"><fmt:message key="order.id"/></th>
        <th style="width: 10%;"><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th style="width: 10%;"><fmt:message key="order.reserve"/></th>
        <th style="width: 10%;"><fmt:message key="order.approval"/></th>
        <th style="width: 10%;"><fmt:message key="book.quantity"/></th>
        <th style="width: 10%;"><fmt:message key="admin.reader-id"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td class="td-center">
                <a href="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}?${RequestParameter.ORDER_ID}=${order.id}"> ${order.id} </a>
            </td>
            <td class="td-center">${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td style="text-align: center;">${order.reserveStatus.title}</td>
            <td style="text-align: center;">
                <c:choose>
                    <c:when test="${order.approved}">
                        <p style="color: green"><b><fmt:message key="order.approved"/></b></p>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red"><b><fmt:message key="order.not-approved"/></b></p>
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="td-center">${order.bookDto.quantity}</td>
            <td class="td-center">${order.userDto.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</c:if>
</body>
</html>