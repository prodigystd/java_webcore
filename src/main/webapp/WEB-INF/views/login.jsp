<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Static content -->
    <link rel="stylesheet" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>

    <title>Spring Boot</title>
</head>
<body>
<h1>Login</h1>
<hr>

<div class="form">
    <form action="/login" method="post" onsubmit="return validate()">
        <table>
            <tr>
                <td><span>${logout}</span> </td>
            </tr>
            <tr>
                <td><input id="username" name="username" placeholder="Username"></td>
                </tr>
            <tr>
                <td><span>${error}</span> </td>
            </tr>
            <tr>
                <td><input id="password" type="password" name="password" placeholder="Password"></td>
                </tr><tr>
                <td><input type="submit" value="Log in"></td>
            </tr>
            <tr>
                <td><h4 class="text-center"><a href="/register">Register</a></h4></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    </form>
</div>

</body>
</html>