<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Student creating</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script>
        $( function() {
            $( "#date-ofadmission" ).datepicker();
        } );
    </script>
</head>
<body>
<aside class="navigation">
    <a href="/home" class="navigation-item">На главную</a>
    <a href="/students" class="navigation-item">Назад</a>

</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <c:choose>
        <c:when test="${isCreated==1}">
            <h2 class="heading-secondary">Для создания студента заполните все поля и нажмите кнопку "Создать"</h2>
            <form action="/student-creating" method="post">
        </c:when>
        <c:otherwise>
            <h2 class="heading-secondary">Для модификации введите новые значения и нажмите "Применить".</h2>
            <form action="/student-modifying" method="post">
        </c:otherwise>
    </c:choose>
            <input type="hidden" value="${modifStudent.id}" name="modifStudId">
    <div class="sername-window">
        <label for="sername">Фамилия</label>
        <input type="text" id="sername" name="newSername" value="${modifStudent.sername}">
    </div>
    <div class="name-window">
        <label for="ownName">Имя</label>
        <input type="text" id="ownName" name="newName" value="${modifStudent.name}">
    </div>
    <div class="group-window">
        <label for="group">Группа</label>
        <input type="text" id="group" name="newGroup" value="${modifStudent.group}">
    </div>
    <div class="date-window">
        <label for="date-ofadmission">Дата поступления</label>
        <input type="text" id="date-ofadmission" name="newDate" value="${modifStudent.date}">
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
