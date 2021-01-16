<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/recourses/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="/home" class="navigation-item">На главную</a>
    <a href="/disciplines" class="navigation-item">Назад</a>
</aside>
<main class="main">

    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <c:choose>
        <c:when test="${isCreated==1}">
            <h2 class="heading-secondary">Для создания новой дисциплины заполните все поля и нажмите "Создать":</h2>
            <form action="/discipline-creating" method="post">
        </c:when>
        <c:otherwise>
            <h2 class="heading-secondary">Для того, чтобы модифицировать дисциплину, введите новое значение и нажмите "Применить".</h2>
            <form action="/discipline-modifying" method="post">
        </c:otherwise>
    </c:choose>
    <input type="hidden" value="${modifDiscipline.discId}" name="idDisc">
        <div>
            <label>Название</label>
            <input type="text" name="newDisc" value="${modifDiscipline.discName}">
        </div>
        <c:choose>
            <c:when test="${isCreated==1}">
                <button class="send-button">Создать</button>
            </c:when>
            <c:otherwise>
                <button class="send-button">Применить</button>
            </c:otherwise>
        </c:choose>
            <c:choose>
                <c:when test="${message==1}">
                    <h3>Поля не должны быть пустыми</h3>
                </c:when>
            </c:choose>

    </form>
</main>
<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
</body>

</html>
