<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Book catalog</title>
    <style>
        table{
            width: 900px;
            border-collapse: collapse;
        }
        td, th{
            border: 1px solid black;
        }
        #column{
            vertical-align: center;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Список книг в каталоге:</h2>
<table>
    <thead>
        <tr>
            <th>Код</th>
            <th>Название</th>
            <th>Авторы</th>
            <th>Категория</th>
            <th>Количество</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="catalog" items="${bookCatalog}" >
            <tr>
                <td>${catalog.book.id}</td>
                <td>${catalog.book.title}</td>
                <td>
                    <c:forEach var="author" items="${catalog.book.authors}">
                        ${author.firstName} ${author.lastName}<br>
                    </c:forEach>
                </td>
                <td>${catalog.book.category.name}</td>
                <td id="column">${catalog.number}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>