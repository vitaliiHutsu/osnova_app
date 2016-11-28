<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <h3 align="center">Редактировать маршрут ${routeEdit.name_route}</h3>

        <div class="col-md-3">
            <div class="form-group">
                <fieldset>
                    <legend>Выбери станцию:</legend>
                    <form method="post" action="/admin/schedule/editRoute">

                        <select class="form-control" name="stationId" id="list_station">
                            <option disabled>Выбери станцию:</option>
                            <c:forEach items="${dbStations}" var="station">
                                <option value="${station.id}"> ${station.title_station}</option>
                            </c:forEach>
                        </select>


                        <label for="position">Порядковый номер:</label>
                        <input id="position" name="position"
                               value="${position.intValue()}" type="number" class="form-control">

                        <button type="submit" class="btn btn-default">Добавить</button>
                    </form>
                </fieldset>
                <fieldset>
                    <legend>Новая остановка:</legend>
                    <form:form modelAttribute="newStation" method="post">
                        <form:errors class="error" path="title_station" display="block"/><br>
                        <form:label path="title_station">Добавить остановку:</form:label>
                        <form:input path="title_station" class="form-control" id="title_station" type="text"/>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="notStation" id="notStation"/> Платформа?
                            </label>
                        </div>
                        <label for="position1">Порядковый номер:</label>
                        <input id="position1" name="position1"
                               value="${position.intValue()}" type="number" class="form-control">
                        <button type="submit" class="btn btn-default" formaction="/admin/schedule/editRoute/addNewStation">
                            Добавить остановку
                        </button>
                    </form:form>
                </fieldset>
                <fieldset>
                    <legend>Удалить маршрут:</legend>
                    <a href="/admin/schedule/deleteRoute/${routeEdit.id}">удалить</a>
                </fieldset>
            </div>
        </div>

        <div class="col-md-9">
            <table class="table table-bordered">

                <thead>
                <tr>
                    <th width="10%">П.№</th>
                    <th width="70%">станция</th>
                    <th width="20%">удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${stations.size() > 0}">
                    <c:forEach items="${stations}" var="addStation" varStatus="index">
                        <tr>
                            <td>${index.index + 1}</td>
                            <td>${addStation.title_station}</td>
                            <td><a href="/admin/schedule/editRoute/removestation?index=${index.index}">удалить</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>

