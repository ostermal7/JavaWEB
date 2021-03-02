<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Semester creating</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/recourses/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="#" class="navigation-item">На главную</a>
    <a href="#" class="navigation-item">Назад</a>

</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
<c:choose>
    <c:when test="${isCreated==1}">
        <h2 class="heading-secondary">Для создания семестра заполните следующие данные и нажмите кнопку "Создать".</h2>
        <form action="/semester-creating" method="post">
    </c:when>
    <c:otherwise>
        <h2 class="heading-secondary">Для модификации семестра отредактируйте данные и нажмите "Применить".</h2>
        <form action="/semester-modifying" method="post">
    </c:otherwise>
</c:choose>
        <input type="hidden" name="modifySemesterIdHidden" value="${modifSemester.semId}">
    <div class="semestr-length">
        <label for="sem-length">Длительность в неделях</label>
        <input type="text" id="sem-length" name="sem-duration" value="${modifSemester.duration}">
    </div>
    <label for="semestr-disciplines" class="sem-disc">Дисциплины в семестре</label>
    <select name="all-disciplines[]" id="semestr-disciplines" multiple>
        <c:forEach items="${allDisciplines}" var="all">
            <option value="${all.discId}">${all.discName}</option>
        </c:forEach>
    </select>

    <div>
        <c:choose>
            <c:when test="${isCreated==1}">
                <button class="modif-btn">Создать</button>
            </c:when>
            <c:otherwise>
                <button class="modif-btn">Применить</button>
            </c:otherwise>
        </c:choose>
    </div>
    </form>
</main>
<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
</body>
</html>
