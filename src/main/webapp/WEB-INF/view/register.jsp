<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.GuestPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.service.validator.ValidationPattern" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title> <fmt:message key="register.title"/> </title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
        <%@include file='/WEB-INF/css/register.css' %>
    </style>
</head>
<body class="block">
<div align="right">
    <ul class="nav nav-link" >
        <li>
            <a href="${pageContext.request.contextPath}${GuestPath.LOGIN.path}">
                | <fmt:message key="register.link-login"/> |
            </a>
        </li>
        <li>
            <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
        </li>
    </ul>
</div>

<h2><fmt:message key="register.header"/></h2>
<form action="${pageContext.request.contextPath}${GuestPath.REGISTER.path}" method="post">
    <table>
        <tr>
            <th><fmt:message key="register.login"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.login"/>" type="text"
                pattern="${ValidationPattern.LOGIN_PATTERN}"
                placeholder="<fmt:message key="register.login-placeholder"/>"
                required name="${RequestParameter.LOGIN}">
            </td>
            <td>
                <h6><fmt:message key="validation.login"/></h6>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.password"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.password"/>" type="password"
                pattern="${ValidationPattern.PASSWORD_PATTERN}"
                placeholder="<fmt:message key="register.password-placeholder"/>"
                required name="${RequestParameter.PASSWORD}">
            </td>
            <td>
                <h6><fmt:message key="validation.password"/></h6>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.first-name"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.name"/>" type="text"
                pattern="${ValidationPattern.NAME_PATTERN}"
                placeholder="<fmt:message key="register.first-name-placeholder"/>"
                required name="${RequestParameter.FIRST_NAME}">
            </td>
            <td>
                <h6><fmt:message key="validation.name"/></h6>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.last-name"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.name"/>" type="text"
                pattern="${ValidationPattern.NAME_PATTERN}"
                placeholder="<fmt:message key="register.last-name-placeholder"/>"
                required name="${RequestParameter.LAST_NAME}">
            </td>
            <td>
                <h6><fmt:message key="validation.name"/></h6>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.phone-number"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.phone-number"/>" type="number"
                pattern="${ValidationPattern.PHONE_PATTERN}"
                placeholder="<fmt:message key="register.phone-number-placeholder"/>"
                required name="${RequestParameter.PHONE_NUMBER}">
            </td>
            <td>
                <h6><fmt:message key="validation.phone-number"/></h6>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.email"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.email"/>" type="email"
                pattern="${ValidationPattern.EMAIL_PATTERN}"
                placeholder="<fmt:message key="register.email-placeholder"/>"
                required name="${RequestParameter.EMAIL}">
            </td>
            <td>
                <h6><fmt:message key="validation.email"/></h6>
            </td>
        </tr>
    </table>
    <input class="button enter" type="submit" value="<fmt:message key="register.button-register"/>">
    <input class="button reset" type="reset" value="<fmt:message key="register.button-reset"/>">
</form>
<c:if test="${userExists}">
    <c:if test="${not empty login}">
        <h3 class="error"><fmt:message key="register.exists-login"/><br><fmt:message key="register.enter-login"/></h3>
    </c:if>
    <c:if test="${not empty email}">
        <h3 class="error"><fmt:message key="register.exists-email"/><br><fmt:message key="register.enter-email"/></h3>
    </c:if>
</c:if>
<c:if test="${not empty invalidField}">
    <h3 style="color:red"><fmt:message key="register.invalid-header"/><br>
        <c:forEach var="field" items="${invalidField}">
            <c:if test="${field eq 'login'}">- <fmt:message key="register.login"/>;<br></c:if>
            <c:if test="${field eq 'password'}">- <fmt:message key="register.password"/>;<br></c:if>
            <c:if test="${field eq 'firstName'}">- <fmt:message key="register.first-name"/>;<br></c:if>
            <c:if test="${field eq 'lastName'}">- <fmt:message key="register.last-name"/>;<br></c:if>
            <c:if test="${field eq 'phoneNumber'}">- <fmt:message key="register.phone-number"/>;<br></c:if>
            <c:if test="${field eq 'email'}">- <fmt:message key="register.email"/>;<br></c:if>
        </c:forEach>
    </h3>
</c:if>
</body>
</html>