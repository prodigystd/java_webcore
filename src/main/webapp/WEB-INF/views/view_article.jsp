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
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>${Title}</title>
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
            <li><a href="#"><span class="glyphicon glyphicon-plus"></span>Add article</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
            <li><a href="#" onclick="document.forms['logoutForm'].submit()">
                <span class="glyphicon glyphicon-log-out"></span>Log out</a></li>
        </ul>
    </div>
</nav>

<div class="article">
    <div class="header"> <c:out value="${article.articleHeader}" /> </div>
    <div><c:out value="${article.articleContent}" /></div>
    <a href="/my_articles" class="btn btn-default" role="button">Back</a>
    <a href="/article/${article.articleId}/edit" class="btn btn-success" role="button">
        <span class="glyphicon glyphicon-edit"></span>Edit</a>
    <a href="#"
       onclick="if(confirm('Do you want to delete this article?'))document.forms['deleteForm${article.articleId}'].submit()"
       class="btn btn-danger" role="button" >
        <span class="glyphicon glyphicon-remove"></span>Delete</a>
</div>
<form id="deleteForm${article.articleId}" method="POST" action="/article/${article.articleId}/delete">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<script src="${contextPath}/webjars/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
