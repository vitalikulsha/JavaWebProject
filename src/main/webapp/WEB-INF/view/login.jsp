<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Вход в систему</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Вход в систему</h2>
<c:if test="${empty sessionScope.user}">
    <h3 class="error">Пользователь не найден.<br>Повторите попытку</h3>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" placeholder="login" required name="login"><br>
    <input type="password" placeholder="password" required name="password"><br><br>
    <input class="button" type="submit" value="Войти">
</form>
<h4 style="text-align: right;"><a href="${pageContext.request.contextPath}/register"> Регистрация </a></h4>
</body>
</html>