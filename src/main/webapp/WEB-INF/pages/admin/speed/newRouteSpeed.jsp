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
                <label for="listStation">Станции:</label>
                <select class="form-control" name="stations" id="listStation" onchange="location = this.value">
                    <option value="">Выбери станции</option>
                    <c:forEach items="${listStation}" var="selectStation">
                        <option value="/admin/speed/selectStationRoute/${selectStation.id}">
                                ${selectStation.title_station}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <a href="/admin/speed/addNewRoute">Добавить маршрут</a>
        </div>
        <div class="col-md-6">
            <c:if test="${message != null}">
                <div class="form-group">
                    <label class="error" for="listStation">${message}</label>
                </div>
            </c:if>
            <c:if test="${listSelectStation.size() != 0}">
                <ol>
                    <c:forEach items="${listSelectStation}" var="selectStation" varStatus="index">
                        <li style="background-color: #ffffff"><h4>${selectStation.title_station} <a href="/admin/speed/deleteStationList/${index.index}"><img
                                src="../../resources/images/delete.png"
                                alt="удалить"></a></h4></li>
                    </c:forEach>
                </ol>
            </c:if>

        </div>

    </div>
</div>
<jsp:include page="../Footer.jsp"/>
