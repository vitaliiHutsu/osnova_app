<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="form-group">
                <h3 align="center">Участок:</h3>
                <select id="speed" name="forma" class="form-control"
                        onchange="location = this.value;">
                    <option value="">----</option>
                    <c:forEach items="${routeSpeedList}" var="routeSpeed">
                        <option value="/speed/odd/${routeSpeed.id}">
                                ${routeSpeed.title_route_speed}
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty stageList}">
                    <c:if test="${flag}">
                        <a class="menu" href="/speed/even/${routeSpeed.id}">Четный</a>
                    </c:if>
                    <c:if test="${not flag}">
                        <a class="menu" href="/speed/odd/${routeSpeed.id}">Нечетный</a>
                    </c:if>
                </c:if>
            </div>
        </div>
        <div class="col-md-9">

            <c:if test="${empty stageList}">
                <h3 align="center">Последние изменения</h3>
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th width="40%">Дата</th>
                                <th width="60%">Станция</th>
                            </tr>
                            </thead>

                            <c:forEach items="${stationListOrderByDate}" var="stationDate">
                                <tbody>
                                <tr>
                                    <jsp:useBean id="stationDateUpdate" class="java.util.Date"/>
                                    <jsp:setProperty name="stationDateUpdate" property="time"
                                                     value="${stationDate.date}"/>
                                    <td><fmt:formatDate value="${stationDateUpdate}" pattern="dd-MM-yyyy"/></td>
                                    <td>${stationDate.title_station}</td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th width="40%">Дата</th>
                                <th width="60%">Перегон</th>
                            </tr>
                            </thead>

                            <c:forEach items="${stageListOrderByDate}" var="stageDate">
                                <tbody>
                                <tr>
                                    <jsp:useBean id="stageDateUpdate" class="java.util.Date"/>
                                    <jsp:setProperty name="stageDateUpdate" property="time"
                                                     value="${stageDate.date}"/>
                                    <td><fmt:formatDate value="${stageDateUpdate}" pattern="dd-MM-yyyy"/></td>
                                    <td>${stageDate.startStation}-${stageDate.endStation}</td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>


            <c:if test="${not empty stageList}">
                <c:if test="${flag}">
                    <h3 align="center">${routeSpeed.title_route_speed}(Н)</h3>
                    <table class="table-bordered table speed-table">
                        <thead>
                        <tr>
                            <th width="20%">Станция</th>
                            <th width="5%">пасс</th>
                            <th width="5%">груз</th>
                            <th width="5%">бок</th>
                            <th width="65%">Примечания</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${routeSpeed.stations}" var="station" varStatus="status">
                            <tr>
                                <td>
                                    <p class="station-title">${station.title_station}</p>
                                        ${station.km_pk}
                                </td>
                                <td>${station.speed_chief_odd_pass}</td>
                                <td>${station.speed_chief_odd_freight}</td>
                                <td>${station.branch_track}</td>
                                <td>${station.notation}</td>
                            </tr>
                            <c:forEach items="${stageList}" var="stage" begin="${status.index}" end="${status.index}">
                                <tr>
                                    <td>

                                    </td>
                                    <td>${stage.speed_pass_odd}</td>
                                    <td>${stage.speed_freight_odd}</td>
                                    <td colspan="2">${stage.notation_odd}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <c:if test="${not flag}">
                    <h3 align="center">${routeSpeed.title_route_speed}(Ч)</h3>
                    <table class="table-bordered table speed-table">
                        <thead>
                        <tr>
                            <th width="20%">Станция</th>
                            <th width="5%">пасс</th>
                            <th width="5%">груз</th>
                            <th width="5%">бок</th>
                            <th width="65%">Примечания</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${routeSpeed.stations}" var="station" varStatus="status">
                            <tr>
                                <td>
                                    <p class="station-title">${station.title_station}</p>
                                        ${station.km_pk}
                                </td>
                                <td>${station.speed_chief_even_pass}</td>
                                <td>${station.speed_chief_even_freight}</td>
                                <td>${station.branch_track}</td>
                                <td>${station.notation}</td>
                            </tr>
                            <c:forEach items="${stageList}" var="stage" begin="${status.index}" end="${status.index}">
                                <tr>
                                    <td>

                                    </td>
                                    <td>${stage.speed_pass_even}</td>
                                    <td>${stage.speed_freight_even}</td>
                                    <td colspan="2">${stage.notation_even}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>