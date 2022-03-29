<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>

<c:if test="${sessionScope.locale eq 'en'}">
    <a href="${pageContext.request.contextPath}${UserPath.LOCALE.path}?${Parameter.LOCALE}=ru">| RU |</a>
</c:if>
<c:if test="${sessionScope.locale eq 'ru'}">
    <a href="${pageContext.request.contextPath}${UserPath.LOCALE.path}?${Parameter.LOCALE}=en">| EN |</a>
</c:if>