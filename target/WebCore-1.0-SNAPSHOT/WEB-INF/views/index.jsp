<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 22.09.2018
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1>Spring Boot - MVC web application example</h1>
<hr>

<div class="form">
    <form action="hello" method="post" onsubmit="return validate()">
        <table>
            <tr>
                <td>Enter Your name</td>
                <td><input id="name" name="name"></td>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
