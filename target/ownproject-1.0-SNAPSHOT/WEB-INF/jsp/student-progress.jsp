<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student's progress</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="/home" class="navigation-item">На главную</a>
    <a href="/students" class="navigation-item">Назад</a>

</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <h2 class="heading-secondary">Отображена успеваемость для следующего студента:</h2>
    <table class="current-student" border="1">
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        <input type="hidden" value="${studForProg.id}" name="studForProgId">
        <tr>
            <td>${studForProg.sername}</td>
            <td>${studForProg.name}</td>
            <td>${studForProg.group}</td>
            <td>${studForProg.date}</td>
        </tr>
    </table>
    <div class="container">
        <table class="progress-table" border="1">
            <tr>
                <th>Дисциплина</th>
                <th>Оценка</th>
            </tr>
            <c:forEach items="${studDiscAndMarks}" var="disc">
                <tr>
                    <td>${disc.disciplineName}</td>
                    <td>${disc.mark}</td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <form method="post" action="/student-progress" id="chooseSemBtnForm">
            <label for="choose-sem" class="cursa">Выбрать семестр</label>
            <select name="semesterForId" id="choose-sem">
                <c:forEach items="${allSems}" var="s">
                    <c:choose>
                        <c:when test="${s.semId==selected}">
                            <option value="${s.semId}" selected>${s.semName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s.semId}">${s.semName}</option>

                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
                <input type="hidden" name="chooseStudIdHidden" value="${studForProg.id}">
                <button class="modif-btn" type="submit">Выбрать</button>
            </form>
        </div>
    </div>
</main>
<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</body>
</html>
