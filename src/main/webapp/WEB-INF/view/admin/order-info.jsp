<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Value" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-order"/></title>
    <style>
        <%@include file='/WEB-INF/css/book-catalog-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.ALL_ORDERS.path}">
        | <fmt:message key="admin.link-order-list"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">
        | <fmt:message key="admin.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">
        | <fmt:message key="admin.link-exit"/> |
    </a>
</h4>
<c:set var="order" scope="request" value="${order}"/>
<h2><fmt:message key="admin.header-order"/></h2>
<table style="with: 900px; margin: auto;">
    <tr>
        <th><fmt:message key="admin.order-id"/></th>
        <td>${order.id}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.book-id"/></th>
        <td>
            <a href="${pageContext.request.contextPath}${AdminPath.BOOK_INFO.path}?${Parameter.BOOK_ID}=${order.bookDto.id}">
                ${order.bookDto.id} </a>
        </td>
    </tr>
    <tr>
        <th><fmt:message key="admin.reader-id"/></th>
        <td>
            <a href="${pageContext.request.contextPath}${AdminPath.READER_INFO.path}?${Parameter.READER_ID}=${order.userDto.id}">
                ${order.userDto.id} </a>
        </td>
    </tr>
    <tr>
        <th><fmt:message key="admin.order-reserve"/></th>
        <td>${order.reserveStatus.title}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.order-approval"/></th>
        <td>
            <c:choose>
                <c:when test="${order.approved}">
                    <p style="color: green"><b><fmt:message key="admin.order-approved"/></b></p>
                    <c:if test="${order.reserveStatus eq 'REFUND'}">
                        <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                            <input type="hidden" name="${Parameter.ACTION}" value="${Value.CANCEL}">
                            <input type="submit" value="<fmt:message key="admin.order-button-close"/>">
                        </form>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <p style="color:red"><b><fmt:message key="admin.order-not-approved"/></b></p>
                    <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                        <input type="hidden" name="${Parameter.ACTION}" value="${Value.APPROVE}">
                        <input type="submit" value="<fmt:message key="admin.order-button-approve"/>">
                    </form>
                    <form action="${pageContext.request.contextPath}${AdminPath.ORDER_INFO.path}" method="post">
                        <input type="hidden" name="${Parameter.ACTION}" value="${Value.CANCEL}">
                        <input type="submit" value="<fmt:message key="admin.order-button-cancel"/>">
                    </form>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</body>
</html>