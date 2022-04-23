<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.UserPath" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.service.validator.ValidationPattern" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="reader.title-edit-profile"/></title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
        <%@include file='/WEB-INF/css/register.css' %>
    </style>
</head>
<body class="block">
<div style="float: right;">
    <ul class="nav nav-link">
        <li>
            <a href="${pageContext.request.contextPath}${UserPath.READER.path}">
                | <fmt:message key="reader.link-account"/> |
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">
                | <fmt:message key="reader.link-exit"/> |
            </a>
        </li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}${UserPath.EDIT.path}" method="post">
    <table>
        <caption>
            <h2><fmt:message key="reader.header-edit-profile"/></h2>
        </caption>
        <tr>
            <th><fmt:message key="register.first-name"/></th>
            <td>
                <input class="entry-field" title="<fmt:message key="validation.name"/>" type="text"
                pattern="${ValidationPattern.NAME_PATTERN}"
                placeholder="<fmt:message key="register.first-name-placeholder"/>"
                required value="${user.firstName}" name="${RequestParameter.FIRST_NAME}">
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
                required value="${user.lastName}" name="${RequestParameter.LAST_NAME}">
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
                required value="${user.phoneNumber}" name="${RequestParameter.PHONE_NUMBER}">
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
                required value="${user.email}" name="${RequestParameter.EMAIL}">
            </td>
            <td>
                <h6><fmt:message key="validation.email"/></h6>
            </td>
        </tr>
    </table>
    <input class="button save" type="submit" value="<fmt:message key="reader.button-save"/>">
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