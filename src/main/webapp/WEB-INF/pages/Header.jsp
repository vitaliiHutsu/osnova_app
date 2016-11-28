<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <div class="col-md-2"><img src="../../resources/images/logo.jpg" alt=""></div>
                    <div class="col-md-5"><p>"Основа"</p></div>
                    <div class="col-md-3"></div>
                    <div class="col-md-2"><img src="../../resources/images/logoUZ.png" alt=""></div>
                </div>
            </div>

            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">ГЛАВНАЯ</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="/speed">СКОРОСТИ</a></li>
                            <li><a href="/document">ДОКУМЕНТЫ</a></li>
                            <li><a href="/schedule">РАСПИСАНИЕ</a></li>
                        </ul>

                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="/logout">ВЫЙТИ</a></li>
                        </ul>

                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="/admin">АДМИНКА</a></li>
                            </ul>
                        </sec:authorize>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

        </header>

        <div class="content">

