<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <h3 align="center">Добавить станции (платформы) участок ${route_tmp.name_route}</h3>

        <div class="col-md-3">

            <div class="form-group">
                <label for="list_station">Выбери станцию:</label>
                <select class="form-control" name="stations" id="list_station" onchange="location = this.value;">
                    <option value="">----</option>
                    <c:forEach items="${listStation}" var="station">
                        <option value="/admin/schedule/addstation?id=${station.id}">${station.title_station}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <form:form modelAttribute="new_station" method="post">
                    <form:errors class="error" path="title_station" display="block"/><br>
                    <form:label path="title_station">Добавить остановку:</form:label>
                    <form:input path="title_station" class="form-control" id="title_station" type="text"/>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="notStation" id="notStation"/> Платформа?
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default" formaction="/admin/schedule/addNewStation">
                        Добавить остановку
                    </button>
                </form:form>
            </div>

        </div>

        <div class="col-md-9">
            <table class="table table-bordered">

                <thead>
                <tr>
                    <th width="80%">станция</th>
                    <th width="20%">удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${addStations.size() > 0}">
                    <c:forEach items="${addStations}" var="addStation" varStatus="index">
                        <tr>
                            <td>${addStation.title_station}</td>
                            <td><a href="/admin/schedule/removestation?index=${index.index}">удалить</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <c:if test="${addStations.size() > 0}">
                <a class="btn btn-primary" href="/admin/schedule/addNewRoute">Добавить участок</a>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>
