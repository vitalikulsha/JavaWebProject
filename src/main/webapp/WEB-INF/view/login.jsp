<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.REGISTER.path}">
        | <fmt:message key="login.link-registration"/> |
    </a>
    <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
</h4>
<h2><fmt:message key="login.header"/></h2>
<form method="post" action="${pageContext.request.contextPath}${UserPath.LOGIN.path}">
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
    </table>
    <input class="button" type="submit" value="<fmt:message key="login.button-enter"/>">
</form>
<c:if test="${not empty userFound && !userFound}">
    <h3 class="error"><fmt:message key="login.invalid"/><br><fmt:message key="login.try-again"/></h3>
</c:if>
</body>
</html>