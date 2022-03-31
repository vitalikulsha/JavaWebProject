<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Value" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-order-list"/></title>
    <style>
        <%@include file='/WEB-INF/css/reader-style.css' %>
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
<h2><fmt:message key="reader.header-order-list"/></h2>
<c:set var="user" scope="request" value="${user}"/>
<c:if test="${empty userOrders}">
    <h4 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h4>
</c:if>
<c:if test="${not empty userOrders}">
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th><fmt:message key="order.id"/></th>
        <th><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="order.reserve"/></th>
        <th><fmt:message key="order.approval"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${userOrders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${UserPath.READER_ORDER_INFO.path}?${Parameter.ORDER_ID}=${order.id}">
                    ${order.id} </a>
            </td>
            <td>${order.bookDto.id}</td>
            <td>${order.bookDto.title}</td>
            <td>${order.reserveStatus.title}</td>
            <td>
                <c:choose>
                    <c:when test="${order.approved}">
                        <p style="color: green"><b><fmt:message key="order.approved"/></b></p>
                        <c:if test="${order.approved}">
                            <c:if test="${order.reserveStatus ne 'REFUND'}">
                                <form action="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}"
                                      method="post">
                                    <input type="hidden" name="${Parameter.ORDER_ID}" value="${order.id}">
                                    <input type="hidden" name="${Parameter.ACTION}" value="${Value.REFUND}">
                                    <input type="submit" value="<fmt:message key="reader.button-return-book"/>">
                                </form>
                            </c:if>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red"><b><fmt:message key="order.not-approved"/></b></p>
                        <form action="${pageContext.request.contextPath}${UserPath.READER_ORDERS.path}" method="post">
                            <input type="hidden" name="${Parameter.ORDER_ID}" value="${order.id}">
                            <input type="hidden" name="${Parameter.ACTION}" value="${Value.CANCEL}">
                            <input type="submit" value="<fmt:message key="reader.button-cancel-order"/>">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp"/>
</c:if>
</body>
</html>