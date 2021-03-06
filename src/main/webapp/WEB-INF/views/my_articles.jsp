<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>My Articles</title>
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
            <li class="active"><a href="/my_articles">My articles</a></li>
            <li><a href="/add_article"><span class="glyphicon glyphicon-plus"></span>Add article</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
            <li><a href="#" onclick="document.forms['logoutForm'].submit()">
                <span class="glyphicon glyphicon-log-out"></span>Log out</a></li>
        </ul>
    </div>
</nav>

<c:forEach items="${UserArticles}" var="article">
    <div class="article">
        <div class="header"> <a class="link" href="/article/${article.articleId}">
            <c:out value="${article.articleHeader}" /></a></div>
        <div><c:out value="${article.getArticleContentShort(1200)}" /></div>
        <a href="/article/${article.articleId}" class="btn btn-info" role="button">
            <span class="glyphicon glyphicon-arrow-right"></span>View</a>
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
</c:forEach>


<script src="webjars/jquery/1.12.4/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
