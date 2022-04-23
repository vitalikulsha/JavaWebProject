<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="admin.title-books"/></title>
    <style>
          <%@include file='/WEB-INF/css/user.css' %>
          <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<div style="float: right;">
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
<c:if test="${empty catalog}">
    <h4 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h4>
</c:if>
<c:if test="${not empty catalog}">
<table style="width: 900px;">
    <caption>
        <h2><fmt:message key="admin.header-books"/></h2>
    </caption>
    <thead>
    <tr>
        <th style="width: 8%;"><fmt:message key="book.id"/></th>
        <th style="width: 32%;"><fmt:message key="book.title"/></th>
        <th style="width: 20%;"><fmt:message key="book.authors"/></th>
        <th style="width: 30%;"><fmt:message key="book.category"/></th>
        <th><fmt:message key="book.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${catalog}">
        <tr>
            <td class="td-center" style="height: 50px;">
                <a href="${pageContext.request.contextPath}${AdminPath.BOOK_INFO.path}?${RequestParameter.BOOK_ID}=${book.id}">
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
</c:if>
</body>
</html>