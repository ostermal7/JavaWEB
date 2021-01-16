<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/js/functions.js" type="text/javascript"></script>
</head>
<body>
<aside class="navigation">
    <a href="/home" class="navigation-item">На главную</a>
</aside>
<main class="main">
    <h1 class="main-heading">Система управления студентами и их успеваемостью</h1>
    <div class="student-buttons">
        <div class="butt">
                <input type="submit" class="studbutton-like-input-second" onclick="gettingStudentProgress()" value="Просмотреть успеваемость выбранного студента">
        <c:if test="${idRole==1}">
            <form method="get" action="/student-creating" class="butt">
                <button>Создать студента...</button>
            </form>
        </div>
        <div class="butt">
            <input type="submit" class="studbutton-like-input-second" onclick="modifyingStudent()" value="Модифицировать выбранного студента">
            <input type="submit" class="studbutton-like-input" onclick="deleteStudent() " value="Удалить выбранных студентов">
        </div>
        </c:if>
    </div>
    <div>
    <h2 class="heading-secondary">Список студентов</h2>
    <table class="student-table" border="1">
        <tr>
            <th></th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        <c:forEach items="${gettingStudents}" var="st">
        <tr>
            <td><input type="checkbox" value="${st.id}"></td>
            <td>${st.sername}</td>
            <td>${st.name}</td>
            <td>${st.group}</td>
            <td><fmt:formatDate value="${st.date}" pattern="dd/MM/yyyy"></fmt:formatDate> </td>
        </tr>
        </c:forEach>
    </table>
    </div>
    <form action="/students" method="post" id="deleteStudentForm">
        <input type="hidden" id="deleteStudentHidden" name="deleteStudentHidden">
    </form>
    <form action="/student-modifying" method="get" id="modifyStudentForm">
        <input type="hidden" id="modifyStudentHidden" name="modifyStudentHidden">
    </form>
    <form action="/student-progress" method="get" id="progressStudentForm">
        <input type="hidden" id="progressStudentHidden" name="progressStudentHidden">
    </form>
</main>

<aside class="logout">
    <a href="/logout" class="logout-btn">Logout</a>
</aside>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</body>
</html>
