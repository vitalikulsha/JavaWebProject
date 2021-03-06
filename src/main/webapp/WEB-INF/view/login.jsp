<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.GuestPath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="login.title"/></title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
        <%@include file='/WEB-INF/css/login.css' %>
    </style>
    <script type="text/javascript" language="javascript">
        <%@include file='/WEB-INF/script/submit-disabled.js' %>
    </script>
</head>
<body class="block">
<div class="banner"></div>
<div>
    <form name="formReg" method="post" action="${pageContext.request.contextPath}${GuestPath.LOGIN.path}">
        <table>
            <caption>
                <h2><fmt:message key="login.header"/></h2>
            </caption>
            <tr>
                <th><fmt:message key="register.login"/></th>
                <td>
                    <input title="<fmt:message key="validation.login"/>" type="text"
                    onKeyup="checkForm()" placeholder="<fmt:message key="register.login-placeholder"/>"
                    required name="${RequestParameter.LOGIN}">
                </td>
            </tr>
            <tr>
                <th><fmt:message key="register.password"/></th>
                <td>
                    <input title="<fmt:message key="validation.password"/>" type="password"
                    onKeyup="checkForm()" placeholder="<fmt:message key="register.password-placeholder"/>"
                    required name="${RequestParameter.PASSWORD}">
                </td>
            </tr>
        </table>
        <input id="submit" type="submit" value="<fmt:message key="login.button-enter"/>">
    </form>
</div>

<nav class="menu">
    <ul class="nav nav-link" >
        <li class="menu-li">
            <a href="${pageContext.request.contextPath}${GuestPath.REGISTER.path}" class="link">
                | <fmt:message key="login.link-registration"/> |
            </a>
        </li>
        <li class="menu-li">
            <jsp:include page="/WEB-INF/view/template/locale.jsp"/>
        </li>
    </ul>
</nav>

<c:if test="${not empty userFound && !userFound}">
    <h3 class="error"><fmt:message key="login.invalid"/><br><fmt:message key="login.try-again"/></h3>
</c:if>
</body>
</html>