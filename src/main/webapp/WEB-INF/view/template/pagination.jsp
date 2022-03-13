<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty param.page}">
        <h4>Страница 1</h4>
    </c:when>
    <c:otherwise>
        <h4>Страница ${param.page}</h4>
    </c:otherwise>
</c:choose>

<nav style="font-size: 20px; text-align: center;">
    <c:forEach var="page" items="${pages}">
        <a href="${requestScope.url}&page=${page}">| - ${page} - |</a>
    </c:forEach>
</nav>