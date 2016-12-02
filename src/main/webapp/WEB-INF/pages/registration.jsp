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
</head>
<content_body>
    <div class="wrapper">
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
                        <form:form modelAttribute="user" autocomplete="false" cssClass="form-signin" method="post">
                            <h2 class="form-signin-heading">Новый пользователь:</h2>


                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="username"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="name">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="name" class="form-control" placeholder="Имя"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="name"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="surname">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="surname" class="form-control" placeholder="Фамилия"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="surname"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="phone">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="phone" class="form-control" placeholder="Телефон"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="phone"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="email" class="form-control" placeholder="E-mail"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="email"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="position">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="position" class="form-control" placeholder="Должность"
                                                autofocus="true"
                                                autocomplete="false"></form:input>
                                    <form:errors cssClass="error" path="position"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''} ${flagErrorPass ? 'has-error' : ''}">
                                    <form:password path="password" class="form-control"
                                                   placeholder="Пароль"
                                                   autofocus="true"
                                                   autocomplete="false"></form:password>
                                    <form:errors cssClass="error" path="password"></form:errors>
                                    <h5 class="error">${messageError}</h5>
                                </div>
                            </spring:bind>

                            <div class="form-group ${flagErrorPass ? 'has-error' : ''} ${flagErrorPass ? 'has-error' : ''}">
                                <input class="form-control" placeholder="Ещё раз" type="password" name="passValid">
                            </div>

                            <h5 class="error">${messageError}</h5>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Регистрация</button>

                        </form:form>
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