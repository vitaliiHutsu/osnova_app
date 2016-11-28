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
                        <form class="form-horizontal" action="/login" method="post">
                            <c:if test="${error}">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <p class="error">неправильный логин или пароль</p>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Login</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="inputEmail3" name="username"
                                           placeholder="Login">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input type="password" name="password" class="form-control" id="inputPassword3"
                                           placeholder="Password">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block">Войти</button>
                                </div>
                            </div>


                            <c:if test="${!flagRegistr && !lostPasswordFlag}">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-12">
                                        <a class="registr" href="/registration">Регистрация</a>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-12">
                                        <a class="registr" href="/lostPassword">Забыли пароль?</a>
                                    </div>

                                </div>

                            </c:if>


                            <c:if test="${flagRegistr || lostPasswordFlag}">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-12">
                                        <h5 class="error">Ожидайте письма на E-mail</h5>
                                    </div>

                                </div>
                            </c:if>

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