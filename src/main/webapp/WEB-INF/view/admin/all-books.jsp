<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-books"/></title>
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
<h2><fmt:message key="admin.header-books"/></h2>
<c:if test="${empty catalog}">
    <h4 style="text-align: center;"><fmt:message key="admin.orders-empty"/></h4>
</c:if>
<c:if test="${not empty catalog}">
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="book.authors"/></th>
        <th><fmt:message key="book.category"/></th>
        <th><fmt:message key="book.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${catalog}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.BOOK_INFO.path}?${Parameter.BOOK_ID}=${book.id}">
                    ${book.id} </a>
            </td>
            <td>${book.title}</td>
            <td>
                <c:forEach var="author" items="${book.authors}">
                    ${author.firstName} ${author.lastName}<br>
                </c:forEach>
            </td>
            <td>${book.category.name}</td>
            <td id="column">${book.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</c:if>
</body>
</html>