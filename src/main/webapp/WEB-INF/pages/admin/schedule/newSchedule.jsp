<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <form:form modelAttribute="schedule" method="post">
            <div class="col-md-2">
                <div class="form-group">
                    <form:errors class="error" path="number_train" display="block"/><br>
                    <form:label path="number_train">Номер поезда:</form:label>
                    <form:input path="number_train" class="form-control" id="number_train" type="number"/>
                    <c:if test="${flagUpdate}">
                        <button type="submit" formaction="/admin/schedule/updateSchedule" class="btn btn-default">
                            изменить
                        </button>
                    </c:if>
                    <c:if test="${not flagUpdate}">
                        <button type="submit" class="btn btn-default">Добавить</button>
                    </c:if>

                    <div class="form-group">
                        <input type="checkbox" name="sendMessage" value="true">Отослать сообщение?
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="15%">№</th>
                        <th width="55%">Станция</th>
                        <th width="15%">приб.</th>
                        <th width="15%">отпр.</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schedule.departureAndArrivals}" var="st_or_time" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                    ${st_or_time.station.title_station}
                            </td>
                            <td>
                                <input class="form-control" type="time"
                                       name="departureAndArrivals[${status.index}].arrival"
                                       value="${st_or_time.arrival}"/>
                            </td>
                            <td>
                                <input class="form-control" type="time"
                                       name="departureAndArrivals[${status.index}].departure"
                                       value="${st_or_time.departure}"/>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-md-4">
                <div class="form-group">
                    <form:label path="notation">Примечание:</form:label>
                    <form:textarea class="form-control" path="notation" id="notation" width="100%" rows="10"
                                   value="${schedule.notation}"/>
                </div>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="../Footer.jsp"/>
