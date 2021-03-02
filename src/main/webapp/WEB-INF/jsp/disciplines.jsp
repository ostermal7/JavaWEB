<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>лист с дисциплинами</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="/home" class="navigation-item">На главную</a>
</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <h2 class="heading-secondary">Список дициплин</h2>

    <div class="container">
        <table class="discipline table" border="1">
            <tr>
                <th></th>
                <th class="table-font">Наименования дисциплин</th>
            </tr>
            <c:forEach items="${discArr}" var="d">
            <tr>
                <td><input type="checkbox" value="${d.discId}"></td>
                <td class="table-font">${d.discName}</td>
            </tr>
            </c:forEach>
        </table>
        <c:if test="${idRole==1}">
        <div class="disc-buttons">
            <form method="get" action="/discipline-creating" class="disc-buttons">
                <button>Создать дисциплину</button>
            </form>

            <input type="submit" class="button-like-input" onclick="modifyingDiscipline()" value="Модифицировать выбранную дисциплину">

            <input type="submit" class="button-like-input" onclick="deleteDisciplines()" value="Удалить выбранную дисциплину">
        </div>
        </c:if>
    </div>
    <form action="/disciplines" method="post" id="deleteDiscForm">
        <input type="hidden" id="deleteDiscHidden" name="deleteDiscHidden">
    </form>
    <form action="/discipline-modifying" method="get" id="modifyingDiscForm">
        <input type="hidden" id="modifyingDiscHidden" name="modifyingDiscHidden">
    </form>
</main>
<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</html>
