<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<c:choose>
    <c:when test="${empty param.page}">
        <h4 style="text-align: center;"><fmt:message key="pagination.page"/> 1 </h4>
    </c:when>
    <c:otherwise>
        <h4 style="text-align: center;"><fmt:message key="pagination.page"/> ${param.page} </h4>
    </c:otherwise>
</c:choose>

<nav style="font-size: 20px; text-align: center;">
    <c:forEach var="page" items="${pages}">
        <a href="${requestScope.url}&page=${page}">| - ${page} - |</a>
    </c:forEach>
</nav>