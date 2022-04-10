<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.GuestPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
        <%@include file='/WEB-INF/css/login.css' %>
    </style>
</head>
<body class="block">
<div class="banner"></div>
<h2><fmt:message key="login.header"/></h2>
<div align="center">
    <form method="post" action="${pageContext.request.contextPath}${GuestPath.LOGIN.path}">
        <table>
            <tr>
                <th><fmt:message key="register.login"/></th>
                <td>
                    <input class="entry-field" title="<fmt:message key="validation.login"/>" type="text"
                    placeholder="<fmt:message key="register.login-placeholder"/>" required name="${RequestParameter.LOGIN}">
                </td>
            </tr>
            <tr>
                <th><fmt:message key="register.password"/></th>
                <td>
                    <input class="entry-field" title="<fmt:message key="validation.password"/>" type="password"
                    placeholder="<fmt:message key="register.password-placeholder"/>" required name="${RequestParameter.PASSWORD}">
                </td>
            </tr>
        </table>
        <input class="enter" type="submit" value="<fmt:message key="login.button-enter"/>">
    </form>
</div>
<h4 class="header">
    <a href="${pageContext.request.contextPath}${GuestPath.REGISTER.path}">
        | <fmt:message key="login.link-registration"/> |
    </a>
    <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
</h4>
<c:if test="${not empty userFound && !userFound}">
    <h3 class="error"><fmt:message key="login.invalid"/><br><fmt:message key="login.try-again"/></h3>
</c:if>
</body>
</html>