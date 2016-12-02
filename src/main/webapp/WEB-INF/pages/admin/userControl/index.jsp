<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <fieldset>
                <legend>Неавторизированные</legend>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>username</th>
                        <th>просмотр</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${usersNotAuthorization}" var="userNotAuthorization">
                        <tr>
                            <td>${userNotAuthorization.username}</td>
                            <td>

                                <button type="button" class="btn btn-primary btn-xs" data-toggle="modal"
                                        data-target=".newUser-${userNotAuthorization.id}">
                                    Подробно
                                </button>

                                <div class="modal fade newUser-${userNotAuthorization.id}" tabindex="-1"
                                     role="dialog"
                                     aria-labelledby="mySmallModalLabel">
                                    <div class="modal-dialog modal-sm" role="newUser">
                                        <div class="modal-content">
                                            <address>
                                                <strong>Username:</strong> <br>
                                                    ${userNotAuthorization.username}
                                            </address>
                                            <address>
                                                <strong>Имя:</strong> <br>
                                                    ${userNotAuthorization.name}
                                            </address>
                                            <address>
                                                <strong>Фамилия:</strong> <br>
                                                    ${userNotAuthorization.surname}
                                            </address>
                                            <address>
                                                <strong>Телефон:</strong> <br>
                                                    ${userNotAuthorization.phone}
                                            </address>
                                            <address>
                                                <strong>E-mail:</strong> <br>
                                                    ${userNotAuthorization.email}
                                            </address>
                                            <address>
                                                <strong>Должность:</strong> <br>
                                                    ${userNotAuthorization.position}
                                            </address>
                                            <address>
                                                <strong>Дата регистрации:</strong> <br>
                                                <jsp:useBean id="dateRegister" class="java.util.Date"/>
                                                <jsp:setProperty name="dateRegister" property="time"
                                                                 value="${userNotAuthorization.date_register}"/>
                                                <fmt:formatDate value="${dateRegister}" pattern="dd-MM-yyyy"/>
                                            </address>


                                            <div class="row">
                                                <div class="col-md-6"><a
                                                        href="/admin/userControl/add/${userNotAuthorization.id}"
                                                        onclick="return confirm('Авторизировать ${userNotAuthorization.username}?')">Авторизировать</a>
                                                </div>
                                                <div class="col-md-6"><a
                                                        href="/admin/userControl/delete/${userNotAuthorization.id}"
                                                        onclick="return confirm('Удалить ${userNotAuthorization.username}?')">Удалить</a>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </fieldset>


        </div>
        <div class="col-md-6">
            <fieldset>
                <legend>Авторизированные</legend>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>username</th>
                        <th>статус</th>
                        <th>управление</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <c:forEach items="${user.userRoles}" var="role">
                                <c:if test="${role.role.equals('ROLE_ADMIN')}">
                                    <td><a href="/admin/userControl/roleUser/${user.id}"
                                           onclick="return confirm('Изменить статус на user?')">админ</a></td>
                                </c:if>
                                <c:if test="${!role.role.equals('ROLE_ADMIN')}">
                                    <td><a href="/admin/userControl/roleAdmin/${user.id}"
                                           onclick="return confirm('Изменить статус на admin?')">user</a></td>
                                </c:if>
                            </c:forEach>
                            <td>
                                <button type="button" class="btn btn-primary btn-xs" data-toggle="modal"
                                        data-target=".user-${user.id}">
                                    Подробно
                                </button>

                                <div class="modal fade user-${user.id}" tabindex="-1" role="dialog"
                                     aria-labelledby="mySmallModalLabel">
                                    <div class="modal-dialog modal-sm" role="user">
                                        <div class="modal-content">
                                            <address>
                                                <strong>Username:</strong> <br>
                                                    ${user.username}
                                            </address>
                                            <address>
                                                <strong>Имя:</strong> <br>
                                                    ${user.name}
                                            </address>
                                            <address>
                                                <strong>Фамилия:</strong> <br>
                                                    ${user.surname}
                                            </address>
                                            <address>
                                                <strong>Телефон:</strong> <br>
                                                    ${user.phone}
                                            </address>
                                            <address>
                                                <strong>E-mail:</strong> <br>
                                                    ${user.email}
                                            </address>
                                            <address>
                                                <strong>Должность:</strong> <br>
                                                    ${user.position}
                                            </address>
                                            <address>
                                                <strong>Дата регистрации:</strong> <br>
                                                <jsp:useBean id="registrDate" class="java.util.Date"/>
                                                <jsp:setProperty name="registrDate" property="time"
                                                                 value="${user.date_register}"/>
                                                <fmt:formatDate value="${registrDate}" pattern="dd-MM-yyyy"/>
                                            </address>


                                            <div class="row">
                                                <div class="col-md-6"><a href="/admin/userControl/ban/${user.id}"
                                                                         onclick="return confirm('Заблокировать ${user.username}?')">Заблокировать</a>
                                                </div>
                                                <div class="col-md-6"><a
                                                        href="/admin/userControl/delete/${user.id}"
                                                        onclick="return confirm('Удалить ${user.username}?')">Удалить</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </fieldset>
        </div>

    </div>
</div>
<jsp:include page="../Footer.jsp"/>
