<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Semester List</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="#" class="navigation-item">На главную</a>
</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <form action="/semester-selection" method="get" id="semIdForm">
    <label for="choose-sem" class="cursa">Выбрать семестр</label>
    <select name="semestrik" id="choose-sem">
        <c:forEach items="${allSemesters}" var="s">
            <c:choose>
                <c:when test="${s.semId==selected}">
                    <option value="${s.semId}" selected name="deletingSemId" id="deletingSemId">${s.semName}</option>
                </c:when>
                <c:otherwise>
                    <option value="${s.semId}">${s.semName}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <button class="modif-btn" type="submit">Выбрать</button>
    </form>
    <h2 class="heading-secondary">Список дисциплин семестра</h2>
    <div class="container">
        <table class="sem-selection-table" border="1">
            <tr>
                <th>Наименование дисциплины</th>
            </tr>
            <c:forEach items="${disciplineOfSem}" var="s">
            <tr>
                <td>${s.discName}</td>
            </tr>
            </c:forEach>
        </table>
        <c:if test="${idRole==1}">
        <div  class="disc-buttons">
            <form action="/semester-creating" class="disc-buttons">
                <button>Создать семестр</button>
            </form>
            <form action="/semester-modifying" class="disc-buttons" method="get" name="modifySemesterIdForm">
                <input type="hidden" value="${s.semId}" name="modifySemesterIdHidden" id="modifySemesterIdHidden">
                <button type="submit" onclick="getModifValue()">Модифицировать текущий семестр</button>
            </form>
            <form action="/semester-selection" class="disc-buttons" method="post" name="deleteSemIdForm">
                <input type="hidden" value="${s.semId}" name="deleteSemIdHidden" id="deleteSemIdHidden">
                <button type="submit" onclick="getValue()">Удалить текущий семестр</button>
            </form>
        </c:if>
        </div>
    </div>
</main>
<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</body>
</html>
