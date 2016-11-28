<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <style>
        .error {
            color: crimson;
            font-size: 1.2em;

        }

        .registr {
            float: left;
        }
    </style>
</head>
<content_body>
    <div class="wrapper content-login">
        <header>
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <h3 align="center">Локомотивное депо "Основа"</h3>
                </div><!-- /.container-fluid -->
            </nav>
        </header>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-4 hidden-sm hidden-xs"></div>
                    <div class="col-md-4">
                        <c:if test="${!messageFlag}">
                            <h4>Введите Ваш e-mail при регистрации</h4>
                        </c:if>

                        <c:if test="${messageFlag}">
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-12">
                                    <h4 class="error">Нет такого e-mail</h4>
                                </div>
                            </div>
                        </c:if>
                        <form class="form-inline" method="post" action="/lostPassword">
                            <div class="form-group">
                                <label for="email">Email: </label>
                                <input type="email" name="email" class="form-control" id="email" placeholder="e-mail">
                            </div>
                            <button type="submit" class="btn btn-primary lostBtn">Востоновить</button>
                        </form>

                    </div>
                    <div class="col-md-4 hidden-sm hidden-xs"></div>
                </div>
            </div>
        </div>

    </div>
    <div class="footer">
        <p>&copy; Hutsu Vitalii</p>
    </div>
    <spring:url value="/resources/js/bootstrap.min.js" var="jsBootstrap"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${jsBootstrap}"></script>
</content_body>
</html>