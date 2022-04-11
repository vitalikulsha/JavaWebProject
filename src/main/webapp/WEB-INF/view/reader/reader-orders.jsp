<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.JspValue" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-order-list"/></title>
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
<h2><fmt:message key="reader.header-order-list"/></h2>
<c:set var="user" scope="request" value="${user}"/>
<c:if test="${empty userOrders}">
    <h3 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h3>
</c:if>
<c:if test="${not empty userOrders}">
<table style="width: 980px;">
    <thead>
    <tr>
        <th style="width: 10%;"><fmt:message key="order.id"/></th>
        <th style="width: 10%;"><fmt:message key="book.id"/></th>
        <th style="width: 40%;"><fmt:message key="book.title"/></th>
        <th style="width: 20%;"><fmt:message key="order.reserve"/></th>
        <th><fmt:message key="order.approval"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${userOrders}">
        <tr>
            <td class="td-center">
                <a href="${pageContext.request.contextPath}${UserPath.READER_ORDER_INFO.path}?${RequestParameter.ORDER_ID}=${order.id}">
                    ${order.id} </a>
            </td>
            <td style="text-align: center;">${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus.title}</td>
            <td style="text-align: center;">
                <c:choose>
                    <c:when test="${order.approved}">
                        <p style="color: green"><b><fmt:message key="order.approved"/></b></p>
                        <c:if test="${order.approved}">
                            <c:if test="${order.reserveStatus ne 'REFUND'}">
                                <form action="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}"
                                      method="post">
                                    <input type="hidden" name="${RequestParameter.ORDER_ID}" value="${order.id}">
                                    <input type="hidden" name="${RequestParameter.ACTION}" value="${JspValue.REFUND}">
                                    <input class="button-approve return" type="submit" value="<fmt:message key="reader.button-return-book"/>">
                                </form>
                            </c:if>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red"><b><fmt:message key="order.not-approved"/></b></p>
                        <form action="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}" method="post">
                            <input type="hidden" name="${RequestParameter.ORDER_ID}" value="${order.id}">
                            <input type="hidden" name="${RequestParameter.ACTION}" value="${JspValue.CANCEL}">
                            <input class="button-approve cancel" type="submit" value="<fmt:message key="reader.button-cancel-order"/>">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
</body>
</html>