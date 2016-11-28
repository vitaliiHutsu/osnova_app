<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/js/jquery-2.2.1.js" var="jquery"/>
<spring:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" var="bootjs"/>
<script src="${bootjs}"></script>
<script src="${jquery}"></script>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:if test="${empty schedule}">
            <div class="col-md-4">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="30%" align="center">№ поезда</th>
                        <th width="70%" align="center">Дата обновления</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schedules}" var="scheduleList" varStatus="status">

                        <jsp:useBean id="date_home" class="java.util.Date"/>
                        <jsp:setProperty name="date_home" property="time" value="${scheduleList.date_add_or_update}"/>
                        <tr>
                            <td><a href="/schedule/${scheduleList.id}">${scheduleList.number_train}</a></td>
                            <td><fmt:formatDate value="${date_home}" pattern="dd-MM-yyyy HH:mm"/></td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${not empty schedule}">

            <div class="col-md-4">
                <h3>№ поезда <span>${schedule.number_train}</span></h3>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="60%">станция</th>
                        <th width="20%">отпр.</th>
                        <th width="20%">приб.</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schedule.departureAndArrivals}" var="scheduleTrain" varStatus="status">
                        <c:if test="${not empty scheduleTrain.departure || not empty scheduleTrain.arrival}">
                            <tr>
                                <td>${scheduleTrain.station.title_station}</td>
                                <c:choose>
                                    <c:when test="${empty scheduleTrain.arrival}">
                                        <td align="center">-</td>
                                    </c:when>
                                    <c:when test="${not empty scheduleTrain.arrival}">
                                        <td align="center">${scheduleTrain.arrival}</td>
                                    </c:when>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${empty scheduleTrain.departure}">
                                        <td align="center">-</td>
                                    </c:when>
                                    <c:when test="${not empty scheduleTrain.departure}">
                                        <td align="center">${scheduleTrain.departure}</td>
                                    </c:when>
                                </c:choose>

                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-md-4">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="30%" align="center">№ поезда</th>
                        <th width="70%" align="center">Дата обновления</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schedules}" var="scheduleList" varStatus="status">

                        <jsp:useBean id="date_home1" class="java.util.Date"/>
                        <jsp:setProperty name="date_home1" property="time" value="${scheduleList.date_add_or_update}"/>
                        <tr>
                            <td><a href="/schedule/${scheduleList.id}">${scheduleList.number_train}</a></td>
                            <td><fmt:formatDate value="${date_home1}" pattern="dd-MM-yyyy HH:mm"/></td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-md-4">

                <jsp:useBean id="dateSchedule" class="java.util.Date"/>
                <jsp:setProperty name="dateSchedule" property="time" value="${schedule.date_add_or_update}"/>
                <h3>Дата обновления: <span><fmt:formatDate value="${dateSchedule}" pattern="dd-MM-yyyy"/> </span></h3>
                <hr/>
                <p class="text-content_body">${schedule.notation}</p>

            </div>

        </c:if>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>