<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.constant.Parameter" %>
<%@ page import="io.github.vitalikulsha.JavaWebProject.util.path.UserPath" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title> <fmt:message key="header.registration" /> </title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h4 style="text-align: right;">
    <a href="${pageContext.request.contextPath}${UserPath.LOGIN.path}">| <fmt:message key="header.login" /> |</a>
    <jsp:include page="/WEB-INF/view/template/locale.jsp" />
</h4>
<h2>Регистрация нового читателя</h2>
<form action="${pageContext.request.contextPath}${UserPath.REGISTER.path}" method="post">
    <table>
        <tr>
            <th>Логин</th>
            <td><input title="Логин может состоять из букв латинского алфовита, цифр, точки, тире, нижнего подчеркивания и иметь длину от 3 до 20 символов"
                    type="text" placeholder="Введите логин" required name="${Parameter.LOGIN}"></td>
        </tr>
        <tr>
            <th>Пароль</th>
            <td><input title="Пароль должен содержать от 8 до 30 символов, одну букву латинского алфавита в нижнем и верхнем регистре, одну цифру."
                    type="password" placeholder="Введите пароль" required name="${Parameter.PASSWORD}"></td>
        </tr>
        <tr>
            <th>Имя пользователя</th>
            <td><input title="Имя или фамилия должны начинаться с заглавной буквы, остальные строчные не более 30 символов латинкого или кириллического алфавита"
                    type="text" placeholder="Введите имя пользователя" required name="${Parameter.FIRST_NAME}"></td>
        </tr>
        <tr>
            <th>Фамилия пользователя</th>
            <td><input title="Имя или фамилия должны начинаться с заглавной буквы, остальные строчные не более 30 символов латинкого или кириллического алфавита"
                    type="text" placeholder="Введите фамилию пользователя" required name="${Parameter.LAST_NAME}"></td>
        </tr>
        <tr>
            <th>Номер телефона</th>
            <td><input title="Номер телефона должен быть в формате 375********* (+9 цифр)"
                    type="number" placeholder="Введите номер телефона" required name="${Parameter.PHONE_NUMBER}"></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input title="E-mail должен быть в формате ***@***.***"
                    type="email" placeholder="Введите e-mail" required name="${Parameter.EMAIL}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="Зарегистрировать">
    <input class="button" style="margin-left: 100px" type="reset" value="Очистить поля">
</form>
<c:if test="${userExists}">
    <c:if test="${not empty login}">
        <h3 class="error">Пользователь с логином '${login}' уже существует.<br>Введите другой логин.</h3>
    </c:if>
    <c:if test="${not empty email}">
        <h3 class="error">Пользователь с e-mail '${email}' уже существует.<br>Введите другой e-mail.</h3>
    </c:if>
</c:if>
<c:if test="${not empty invalidField}">
    <h3 style="color:red"> Некорректно заполненные поля: <br>
        <c:forEach var="field" items="${invalidField}">
            <c:if test="${field eq 'login'}">- логин;<br></c:if>
            <c:if test="${field eq 'password'}">- пароль;<br></c:if>
            <c:if test="${field eq 'firstName'}">- имя пользователя;<br></c:if>
            <c:if test="${field eq 'lastName'}">- фамилия пользователя;<br></c:if>
            <c:if test="${field eq 'phoneNumber'}">- номер телефона;<br></c:if>
            <c:if test="${field eq 'email'}">- e-mail;<br></c:if>
        </c:forEach>
    </h3>
</c:if>
</body>
</html>