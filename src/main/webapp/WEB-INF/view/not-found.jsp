<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Поиск книги</title>
    <style>
        <%@include file='/WEB-INF/css/style.css' %>
    </style>
</head>
<body class="block">
<h2>Ничего не найдено</h2>
<h3 style="text-align: right;"><a href="<%= request.getContextPath() %>/book-search"> Повторить поиск </a></h3>
</body>
</html>