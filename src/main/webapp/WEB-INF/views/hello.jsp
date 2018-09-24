<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Boot</title>
</head>
<body>
<h1>Spring Boot - MVC web application example</h1>
<hr>

<h2>Welcome!</h2>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h4>Welcome ${pageContext.request.userPrincipal.name} </h4>

</c:if>

<table>
    <c:forEach items="${Users}" var="user">
        <tr>
            <td><c:out value="${user.username}" /></td>
            <td><c:out value="${user.password}" /></td>
        </tr>
    </c:forEach>
</table>

<br>
<a onclick="document.forms['logoutForm'].submit()">Logout</a>

</body>
</html>