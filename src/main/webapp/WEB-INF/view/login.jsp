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
<!--<c:if test="${sessionScope.notFound eq 'user'}">-->
<!--    <h3 class="error">Пользователь '${param.login}' не найден.<br>Повторите попытку</h3>-->
<!--</c:if>-->
<!--<c:if test="${sessionScope.notFound eq 'password'}">-->
<!--    <h3 class="error">Пользователь '${param.login}' найден, но не верный пароль.<br>Повторите попытку</h3>-->
<!--</c:if>-->
<form method="post" action="<%= request.getContextPath() %>/login">
    <input type="text" required placeholder="login" name="login"><br>
    <input type="password" required placeholder="password" name="password"><br><br>
    <input class="button" type="submit" value="Войти">
</form>
<h4 style="text-align: right;"><a href="<%= request.getContextPath() %>/register"> Регистрация </a></h4>
</body>
</html>