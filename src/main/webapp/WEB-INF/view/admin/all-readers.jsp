<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.AdminPath" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>

<html>
<head>
    <title>Список читателей</title>
    <style>
        <%@include file='/WEB-INF/css/book-catalog-style.css' %>
        <%@include file='/WEB-INF/css/style.css' %>

    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${AdminPath.ADMIN.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${AdminPath.LOGOUT.path}">| Выйти |</a>
</h4>
<h2>Список всех читателей</h2>
<c:set var="user" scope="request" value="${user}"/>
<table style="with: 900px; margin: auto;">
    <thead>
    <tr>
        <th>Код читателя</th>
        <th>Имя читателя</th>
        <th>Фамилия читателя</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reader" items="${allReaders}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}${AdminPath.READER_INFO.path}?${Parameter.READER_ID}=${reader.id}">
                    ${reader.id} </a>
            </td>
            <td>${reader.firstName}</td>
            <td>${reader.lastName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/view/template/pagination.jsp" />

</body>
</html>