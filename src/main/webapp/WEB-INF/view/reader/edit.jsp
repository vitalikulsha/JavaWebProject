<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="reader.title-edit-profile"/></title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.READER.path}">
        | <fmt:message key="reader.link-account"/> |
    </a>
    <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">
        | <fmt:message key="reader.link-exit"/> |
    </a>
</h4>
<h2><fmt:message key="reader.header-edit-profile"/></h2>
<form action="${pageContext.request.contextPath}${UserPath.EDIT.path}" method="post">
    <table>
        <tr>
            <th><fmt:message key="register.first-name"/></th>
            <td>
                <input title="<fmt:message key="validation.name"/>" type="text"
                placeholder="<fmt:message key="register.first-name-placeholder"/>"
                required value="${user.firstName}" name="${Parameter.FIRST_NAME}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.last-name"/></th>
            <td><input title="<fmt:message key="validation.name"/>" type="text"
                placeholder="<fmt:message key="register.last-name-placeholder"/>"
                required value="${user.lastName}" name="${Parameter.LAST_NAME}"></td>
        </tr>
        <tr>
            <th><fmt:message key="register.phone-number"/></th>
            <td><input title="<fmt:message key="validation.phone-number"/>" type="number"
                placeholder="<fmt:message key="register.phone-number-placeholder"/>"
                required value="${user.phoneNumber}" name="${Parameter.PHONE_NUMBER}"></td>
        </tr>
        <tr>
            <th><fmt:message key="register.email"/></th>
            <td><input title="<fmt:message key="validation.email"/>" type="email"
                placeholder="<fmt:message key="register.email-placeholder"/>"
                required value="${user.email}" name="${Parameter.EMAIL}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="<fmt:message key="reader.button-save"/>">
</form>
<c:if test="${not empty invalidField}">
    <h3 style="color:red"> <fmt:message key="register.invalid-header"/> <br>
        <c:forEach var="field" items="${invalidField}">
            <c:if test="${field eq 'firstName'}">- <fmt:message key="register.first-name"/>;<br></c:if>
            <c:if test="${field eq 'lastName'}">- <fmt:message key="register.last-name"/>;<br></c:if>
            <c:if test="${field eq 'phoneNumber'}">- <fmt:message key="register.phone-number"/>;<br></c:if>
            <c:if test="${field eq 'email'}">- <fmt:message key="register.email"/>;<br></c:if>
        </c:forEach>
    </h3>
</c:if>
</body>
</html>