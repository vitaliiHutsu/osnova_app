<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">


            <div class="form-group">
                <form action="/admin/speed/newStationOnRouteSpeed" id="formAddStationInRoute" method="get">
                    <label form="listStation">Добавить станцию</label>
                    <select class="form-control" id="listStation" name="stationId">
                        <option selected="selected" value="0">Выбери станции</option>
                        <c:forEach items="${listStation}" var="station">
                            <option value="${station.id}">${station.title_station}</option>
                        </c:forEach>
                    </select>

                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="end" checked>
                            Добавить в конец
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="start">
                            Добавить в начало
                        </label>
                    </div>
                    <button type="submit" form="formAddStationInRoute" class="btn btn-default">Добавить</button>
                </form>
                <a href="/admin/speed/deleteRouteSpeed/${routeSpeed.id}">удалить маршрут</a>

            </div>


        </div>
        <div class="col-md-9">
            <h3 align="center">${routeSpeed.title_route_speed}</h3>
            <table class="table-bordered table speed-table">
                <thead>
                <tr>
                    <th colspan="4" width="50%">нечетное направление</th>
                    <th colspan="4" width="50%">четное направление</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listRouteUpdate}" var="routeSpeedUpdate" varStatus="status">
                    <tr>
                        <td width="26%">${listRouteUpdate.get(status.index).notation}</td>
                        <td width="7%">${listRouteUpdate.get(status.index).branch_track}</td>
                        <td width="7%">${listRouteUpdate.get(status.index).speed_chief_odd_pass}/${listRouteUpdate.get(status.index).speed_chief_odd_freight}</td>
                        <td width="20%" colspan="2">
                                ${listRouteUpdate.get(status.index).title_station} <br/>
                                ${listRouteUpdate.get(status.index).km_pk} <br/>
                        </td>
                        <td width="7%">${listRouteUpdate.get(status.index).speed_chief_even_pass}/${listRouteUpdate.get(status.index).speed_chief_even_freight} </td>
                        <td width="7%">${listRouteUpdate.get(status.index).branch_track}</td>
                        <td width="26%">${listRouteUpdate.get(status.index).notation}</td>
                    </tr>
                    <c:forEach items="${listStage}" var="stage" begin="${status.index}" end="${status.index}">
                        <tr>
                            <td colspan="2" width="34%"><p><a
                                    href="/admin/speed/editStage/${stage.id}">${stage.notation_odd}</p></a></td>
                            <td colspan="2" width="16%"><a
                                    href="/admin/speed/editStage/${stage.id}">${stage.speed_pass_odd}/${stage.speed_freight_odd}</a>
                            </td>
                            <td colspan="2" width="16%"><a
                                    href="/admin/speed/editStage/${stage.id}">${stage.speed_pass_even}/${stage.speed_freight_even}</a>
                            </td>
                            <td colspan="2" width="34%"><p><a
                                    href="/admin/speed/editStage/${stage.id}">${stage.notation_even}</p></a></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>
