<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>

<html>
<head>
    <title>Редактировать профиль</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.READER.path}">| Личный кабинет |</a>
    <a href="${pageContext.request.contextPath}${UserPath.LOGOUT.path}">| Выйти |</a>
</h4>
<h2>Редактирование профиля пользователя</h2>
<form action="${pageContext.request.contextPath}${UserPath.EDIT.path}" method="post">
    <table>
        <tr>
            <th>Имя пользователя</th>
            <td>
                <input title="Имя должно содержать только латинские или кириллические буквы. Первая буква заглавная, остальные строчные. Не более 30 символов."
                       type="text" placeholder="Введите имя пользователя" required value="${user.firstName}"
                       name="${Parameter.FIRST_NAME}">
            </td>
        </tr>
        <tr>
            <th>Фамилия пользователя</th>
            <td><input title="Фамилия должна содержать только латинские или кириллические буквы. Первая буква заглавная, остальные строчные. Не более 30 символов."
                    type="text" placeholder="Введите фамилию пользователя" required value="${user.lastName}"
                    name="${Parameter.LAST_NAME}"></td>
        </tr>
        <tr>
            <th>Номер телефона</th>
            <td><input title="Номер телефона в формате 375......... (+9 цифр)"
                       type="number" placeholder="Введите номер телефона" required value="${user.phoneNumber}"
                       name="${Parameter.PHONE_NUMBER}"></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input title="E-mail в формате ...@...[.]..."
                       type="email" placeholder="Введите e-mail" required value="${user.email}"
                       name="${Parameter.EMAIL}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="Сохранить">
</form>
<c:if test="${not empty invalidField}">
    <h3 style="color:red"> Некорректно заполненные поля: <br>
        <c:forEach var="field" items="${invalidField}">
            <c:if test="${field eq 'firstName'}">- имя пользователя;<br></c:if>
            <c:if test="${field eq 'lastName'}">- фамилия пользователя;<br></c:if>
            <c:if test="${field eq 'phoneNumber'}">- номер телефона;<br></c:if>
            <c:if test="${field eq 'email'}">- e-mail;<br></c:if>
        </c:forEach>
    </h3>
</c:if>
</body>
</html>