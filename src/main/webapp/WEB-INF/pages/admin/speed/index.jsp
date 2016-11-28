<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <fieldset>
                <legend>Новая станция:</legend>
                <a href="/admin/speed/addStation">Новая станция</a>
            </fieldset>
            <fieldset>
                <legend>Редактировать станцию:</legend>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>станция</th>
                        <th>дата</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listStation}" var="station">
                    <tr>
                        <td><a href="/admin/speed/editStation/${station.id}">${station.title_station}</a></td>
                        <jsp:useBean id="stationDate" class="java.util.Date"/>
                        <jsp:setProperty name="stationDate" property="time" value="${station.date}"/>
                        <td><fmt:formatDate value="${stationDate}" pattern="dd-MM-yyyy"/></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </fieldset>

        </div>
        <div class="col-md-6">
            <fieldset>
                <legend>Новый участок</legend>
                <a href="/admin/speed/newRouteSpeed">Новый участок</a>
            </fieldset>

            <fieldset>
                <legend>Редактировать участок:</legend>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>участок</th>
                        <th>дата</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listRouteSpeed}" var="routeSpeed">
                        <tr>
                            <td><a href="/admin/speed/editRouteSpeed/${routeSpeed.id}">${routeSpeed.title_route_speed}</a></td>
                            <jsp:useBean id="routeDate" class="java.util.Date"/>
                            <jsp:setProperty name="routeDate" property="time" value="${routeSpeed.date}"/>
                            <td><fmt:formatDate value="${routeDate}" pattern="dd-MM-yyyy"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>