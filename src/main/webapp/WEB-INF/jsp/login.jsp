<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/recourses/js/functions.js" type="text/javascript"></script>
</head>
<body>
<main class="main">
    <div class="container-login">
        <form action="/login" method="post">
        <label for="username">Username</label>
        <input type="text" class="log-and-pass" placeholder="Enter Username" name="username" id="username">

        <label for="pass">Password</label>
        <input type="text"class="log-and-pass" placeholder="Enter Password" name="pass" id="pass">

        <label for="role">Role</label>
            <div>
        <select name="role" id="role" class="log-and-pass">
            <option value="1">Администратор</option>
            <option value="2">Учитель</option>
            <option value="3">Студент</option>
        </select>
            </div>

        <button type="submit" class="log-btn">Login</button>
        </form>
        <c:choose>
            <c:when test="${message eq 1}">
                <h4>Неверно введены данные!</h4>
            </c:when>
        </c:choose>
    </div>
</main>
<div style="position: fixed; right: 20px; bottom: 0px;">
    <p>Тестовые аккаунты: "admin / 123" , "student / 12345"</p>
    <p>Аккаунт "admin" поддерживает обе роли</p>
</div>
</body>
</html>
