<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <fieldset>
                <legend>Добавить новое расписание</legend>
                <div class="form-group">
                    <label for="list_route">Выбери маршрут:</label>
                    <select class="form-control" name="list_route" id="list_route" onchange="location = this.value;">
                        <option value="">----</option>
                        <c:forEach items="${listRoute}" var="route">
                            <option value="/admin/schedule/addNewSchedule?id=${route.id}">${route.name_route}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>
        </div>
        <div class="col-md-3">
            <fieldset>
                <legend>Новый маршрут:</legend>
                <form:form modelAttribute="route" method="post" id="route_form" action="/admin/schedule/newroute">
                    <div class="form-group">
                        <form:errors class="error" path="name_route" display="block"/><br>
                        <form:label path="name_route">Участок:</form:label>
                        <form:input class="form-control" path="name_route" id="name_route" type="text"/>
                    </div>
                    <button type="submit" class="btn btn-default" form="route_form">
                        Добавить
                    </button>
                </form:form>
            </fieldset>

            <fieldset>
                <legend>Редактировать маршрут</legend>
                <div class="form-group">
                    <label for="list_route">Выбери направление:</label>
                    <select class="form-control" name="list_route" id="list_route" onchange="location = this.value;">
                        <option value="">----</option>
                        <c:forEach items="${listRoute}" var="route">
                            <option value="/admin/schedule/editRoute/${route.id}">${route.name_route}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>
        </div>
        <div class="col-md-6">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th align="center" width="10%">П.№</th>
                    <th align="center" width="30%">№ поезда</th>
                    <th align="center" width="30%">изменить</th>
                    <th align="center" width="30%">удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listSchedule}" var="scheduleList" varStatus="index">
                    <tr>
                        <td>${index.index}</td>
                        <td>${scheduleList.number_train}</td>
                        <td><a href="/admin/schedule/editSchedule/${scheduleList.id}">изменить</a></td>
                        <td><a href="/admin/schedule/deleteSchedule/${scheduleList.id}">удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>