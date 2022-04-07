<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.javawebproject.util.constant.RequestParameter" %>
<%@ page import="io.github.vitalikulsha.javawebproject.servlet.path.GuestPath" %>

<c:if test="${sessionScope.locale eq 'en'}">
    <a href="${pageContext.request.contextPath}${GuestPath.LOCALE.path}?${RequestParameter.LOCALE}=ru">| RU |</a>
</c:if>
<c:if test="${sessionScope.locale eq 'ru'}">
    <a href="${pageContext.request.contextPath}${GuestPath.LOCALE.path}?${RequestParameter.LOCALE}=en">| EN |</a>
</c:if>
