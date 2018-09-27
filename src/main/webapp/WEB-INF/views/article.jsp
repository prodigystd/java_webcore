<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>${Action}</title>
</head>
<body>
<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default"> <!--style="background-color: #eee;"-->
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">WebCore</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">Main</a></li>
            <li><a href="/my_articles">My articles</a></li>
            <li class="active"><a href="#"><span class="glyphicon glyphicon-plus"></span>Add article</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
            <li><a href="#" onclick="document.forms['logoutForm'].submit()">
                <span class="glyphicon glyphicon-log-out"></span>Log out</a></li>
        </ul>
    </div>
</nav>

<form:form method="POST" modelAttribute="userArticle" action="/add_article">
    <spring:bind path="articleHeader">
    <div class="form-group ${status.error ? 'has-error' : ''}">
    <form:input type="text" path="articleHeader" class="form-control" id="usr" placeholder="Article header"
                autofocus="true"></form:input>
    <form:errors path="articleHeader"></form:errors>
    </div>
    </spring:bind>

    <spring:bind path="articleContent">
    <div class="form-group ${status.error ? 'has-error' : ''}">
    <form:textarea class="form-control" path="articleContent" placeholder="Article content..." rows="20"
                   ></form:textarea>
    <form:errors path="articleContent"></form:errors>
        </div>
    </spring:bind>
<div class="btn-group">
    <a href="/my_articles" class="btn btn-default" role="button">Back</a>
</div>
<div class="btn-group">
    <button type="submit" class="btn btn-success">Save</button>
</div>
</form:form>
</div>

<script src="${contextPath}/webjars/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
