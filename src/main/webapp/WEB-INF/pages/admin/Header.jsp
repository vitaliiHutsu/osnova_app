<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/css/style.css" var="style"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap"/>
    <link rel="stylesheet" href="${style}"/>
    <link rel="stylesheet" href="${bootstrap}"/>
    <title>Формуляр</title>
</head>
<content_body>
<div class="wrapper">
    <header>
        <div class="container hidden-sm hidden-xs">
            <div class="row">
                <div class="col-md-5"><p>Формуляр</p></div>
            </div>
        </div>

        <nav id="header-menu" class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/admin">ГЛАВНАЯ</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">

                        <li><a href="/admin/document">ДОКУМЕНТЫ</a></li>

                        <li><a href="/admin/speed">СКОРОСТИ</a></li>

                        <li><a href="/admin/schedule">РАСПИСАНИЯ</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/">HOME</a></li>
                        <li><a href="/admin/userControl">USERS</a></li>
                        <li><a href="/logout">ВЫЙТИ</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </header>

    <div class="content">

