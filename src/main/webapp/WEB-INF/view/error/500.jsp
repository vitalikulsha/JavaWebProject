<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="libraryTags" prefix="lt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>500</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<p style="text-align: center">
    <img src="${pageContext.request.contextPath}/img/500-error.png" alt="500" width="250px"/>
</p>
<lt:err-msg error="500" locale="${locale}"/>
</body>
</html>