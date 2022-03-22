<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Регистрация нового пользователя</h2>
<form action="${pageContext.request.contextPath}${UserPath.REGISTER.path}" method="post">
    <table>
        <tr>
            <th>Логин</th>
            <td><input type="text" placeholder="Введите логин" required name="${Parameter.LOGIN}"></td>
        </tr>
        <tr>
            <th>Пароль</th>
            <td><input type="password" placeholder="Введите пароль" required name="${Parameter.PASSWORD}"></td>
        </tr>
        <tr>
            <th>Имя пользователя</th>
            <td><input type="text" placeholder="Введите имя пользователя" required name="${Parameter.USERNAME}"></td>
        </tr>
        <tr>
            <th>Номер телефона</th>
            <td><input type="number" placeholder="Введите номер телефона" required name="${Parameter.PHONE_NUMBER}"></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input type="email" placeholder="Введите e-mail" required name="${Parameter.EMAIL}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="Зарегистрировать">
</form>
<c:if test="${isExists}">
    <c:if test="${not empty login}">
        <h3 class="error">Пользователь с логином '${login}' уже существует.<br>Введите другой логин.</h3>
    </c:if>
    <c:if test="${not empty email}">
        <h3 class="error">Пользователь с e-mail '${email}' уже существует.<br>Введите другой e-mail.</h3>
    </c:if>
</c:if>
</body>
</html>