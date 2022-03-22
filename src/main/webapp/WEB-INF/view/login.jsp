<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>

<html>
<head>
    <title>Вход в систему</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Вход в систему</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" placeholder="login" required name="${Parameter.LOGIN}"><br>
    <input type="password" placeholder="password" required name="${Parameter.PASSWORD}"><br><br>
    <input class="button" type="submit" value="Войти">
</form>
<h4 style="text-align: right;"><a href="${pageContext.request.contextPath}/register"> Регистрация </a></h4>
<c:if test="${not empty userFound}">
    <c:if test="${!userFound}">
        <h3 class="error">Неверный логин или пароль<br>Повторите попытку</h3>
    </c:if>
</c:if>
</body>
</html>