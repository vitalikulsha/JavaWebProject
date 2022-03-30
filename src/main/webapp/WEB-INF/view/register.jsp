<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title> <fmt:message key="register.title"/> </title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.LOGIN.path}">
        | <fmt:message key="register.link-login"/> |</a>
    <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
</h4>
<h2><fmt:message key="register.header"/></h2>
<form action="${pageContext.request.contextPath}${UserPath.REGISTER.path}" method="post">
    <table>
        <tr>
            <th><fmt:message key="register.login"/></th>
            <td>
                <input title="<fmt:message key="validation.login"/>" type="text"
                placeholder="<fmt:message key="register.login-placeholder"/>" required name="${Parameter.LOGIN}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.password"/></th>
            <td>
                <input title="<fmt:message key="validation.password"/>" type="password"
                placeholder="<fmt:message key="register.password-placeholder"/>" required name="${Parameter.PASSWORD}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.first-name"/></th>
            <td>
                <input title="<fmt:message key="validation.name"/>" type="text"
                placeholder="<fmt:message key="register.first-name-placeholder"/>" required name="${Parameter.FIRST_NAME}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.last-name"/></th>
            <td>
                <input title="<fmt:message key="validation.name"/>" type="text"
                placeholder="<fmt:message key="register.last-name-placeholder"/>" required name="${Parameter.LAST_NAME}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.phone-number"/></th>
            <td>
                <input title="<fmt:message key="validation.phone-number"/>" type="number"
                placeholder="<fmt:message key="register.phone-number-placeholder"/>" required name="${Parameter.PHONE_NUMBER}">
            </td>
        </tr>
        <tr>
            <th><fmt:message key="register.email"/></th>
            <td>
                <input title="<fmt:message key="validation.email"/>" type="email"
                placeholder="<fmt:message key="register.email-placeholder"/>" required name="${Parameter.EMAIL}">
            </td>
        </tr>
    </table>
    <input class="button" type="submit" value="<fmt:message key="register.button-register"/>">
    <input class="button" style="margin-left: 100px" type="reset" value="<fmt:message key="register.button-reset"/>">
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