<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<html>
<head>
    <title>Редактировать профиль</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Редактирование профиля пользователя</h2>
<form action="${pageContext.request.contextPath}/reader/edit" method="post">
    <table>
        <tr>
            <th>Имя пользователя</th>
            <td><input type="text" placeholder="Введите имя пользователя" required value="${user.userName}"
                       name="${Parameter.USERNAME}"></td>
        </tr>
        <tr>
            <th>Номер телефона</th>
            <td><input type="number" placeholder="Введите номер телефона" required value="${user.phoneNumber}"
                       name="${Parameter.PHONE_NUMBER}"></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input type="email" placeholder="Введите e-mail" required value="${user.email}"
                       name="${Parameter.EMAIL}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="Сохранить">
</form>
</body>
</html>