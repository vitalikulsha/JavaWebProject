<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="admin.title-readers"/></title>
    <style>
        <%@include file='/WEB-INF/css/user.css' %>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<nav class="menu">
    <ul class="nav nav-link">
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}" class="link">
                | <fmt:message key="admin.link-account"/> |
            </a>
        </li>
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}" class="link">
                | <fmt:message key="admin.link-exit"/> |
            </a>
        </li>
    </ul>
</nav>
<c:if test="${empty allReaders}">
    <h4 style="text-align: center;"><fmt:message key="admin.readers-empty"/></h4>
</c:if>
<c:if test="${not empty allReaders}">
<c:set var="user" scope="request" value="${user}"/>
<table style="width: 500px;">
    <caption>
        <h2><fmt:message key="admin.header-readers"/></h2>
    </caption>
    <thead>
    <tr>
        <th style="width: 10%;"><fmt:message key="admin.reader-id"/></th>
        <th style="width: 45%;"><fmt:message key="admin.reader-first-name"/></th>
        <th><fmt:message key="admin.reader-last-name"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reader" items="${allReaders}">
        <tr>
            <td class="td-center">
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