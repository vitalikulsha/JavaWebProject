<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Регистрация нового пользователя</h2>
<form method="post" action="<%= request.getContextPath() %>/reader">
    <input type="text" required placeholder="login" name="login"><br>
    <input type="password" required placeholder="password" name="password"><br>
    <input type="text" required placeholder="userName" name="userName"><br>
    <input type="number" required placeholder="phoneNumber" name="phoneNumber"><br>
    <input type="email" required placeholder="email" name="email"><br><br>
    <input class="button" type="submit" value="Зарегистрировать">
</form>
</body>
</html>