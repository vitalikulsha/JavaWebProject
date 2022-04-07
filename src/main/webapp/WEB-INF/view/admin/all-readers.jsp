<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="admin.title-readers"/></title>
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
<c:if test="${empty allReaders}">
    <h4 style="text-align: center;"><fmt:message key="admin.readers-empty"/></h4>
</c:if>
<c:if test="${not empty allReaders}">
<c:set var="user" scope="request" value="${user}"/>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th><fmt:message key="admin.reader-id"/></th>
        <th><fmt:message key="admin.reader-first-name"/></th>
        <th><fmt:message key="admin.reader-last-name"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reader" items="${allReaders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.READER_INFO.path}?${RequestParameter.READER_ID}=${reader.id}">
                    ${reader.id} </a>
            </td>
            <td>${reader.firstName}</td>
            <td>${reader.lastName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/view/template/pagination.jsp" />
</c:if>
</body>
</html>