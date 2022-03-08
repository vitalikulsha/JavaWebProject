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
<form method="post" action="${pageContext.request.contextPath}/reader">
    <table>
        <tr>
            <th>Логин</th>
            <td><input type="text" placeholder="Введите логин" required name="login"></td>
        </tr>
        <tr>
            <th>Пароль</th>
            <td><input type="password" placeholder="Введите пароль" required name="password"></td>
        </tr>
        <tr>
            <th>Имя пользователя</th>
            <td><input type="text" placeholder="Введите имя пользователя" required name="userName"></td>
        </tr>
        <tr>
            <th>Номер телефона</th>
            <td><input type="number" placeholder="Введите номер телефона" required name="phoneNumber"></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input type="email" placeholder="Введите e-mail" required name="email"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="Зарегистрировать">
</form>
</body>
</html>