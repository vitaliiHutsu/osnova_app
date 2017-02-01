<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" charset="UTF-8">
    <%@include file="../../../resources/js/jquery-3.1.1.min.js"%>
</script>
<script type="text/javascript" charset="UTF-8">
    <%@include file="../../../resources/js/tra_speed_station.js"%>
</script>
<jsp:include page="../Header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <form:form modelAttribute="station" method="post">
        <div class="col-md-6">
            <div class="row">
                <c:if test="${flagEdit}">
                    <div class="col-md-6">

                        <div class="form-group">
                            <form:label path="title_station"> Название станции:</form:label>
                            <form:input path="title_station"
                                        id="title_station" type="text"
                                        value="${station.title_station}" cssClass="form-control"
                                        disabled="true"/>
                        </div>

                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                                <%--<form:label path="km_pk">КМ.ПК станции</form:label>--%>
                                <%--<form:input path="km_pk" id="km_pk" value="${station.km_pk}"--%>
                                <%--cssClass="form-control"--%>
                                <%--type="number"--%>
                                <%--step="any"--%>
                                <%--min="0"/>--%>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!flagEdit}">
                    <div class="col-md-6">

                        <div class="form-group">

                            <form:label path="title_station"> Название станции:</form:label>
                            <form:input path="title_station"
                                        id="title_station" type="text"
                                        value="${station.title_station}" cssClass="form-control"/>
                            <form:errors class="error" path="title_station" display="block"/><br>

                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="notStation" value="${station.notStation}"
                                               id="notStation"/> Платформа?
                            </label>
                        </div>

                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <form:label path="km_pk">КМ.ПК станции</form:label>
                            <form:input path="km_pk" id="km_pk" value="${station.km_pk}"
                                        cssClass="form-control"
                                        type="number"
                                        step="any"
                                        min="0"/>
                        </div>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <form:label path="notation">Примечание</form:label>
                <form:textarea class="form-control" path="notation" id="notation" value="${station.notation}" cols="21"
                               rows="10"/>
            </div>
        </div>
        <div class="col-md-6">
            <h3 align="center">Скорости по станции</h3>

            <div class="row">
                <div class="col-md-6">
                    <fieldset>
                        <legend>Чётный:</legend>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <form:label path="speed_chief_even_pass">ГЛ.ПТ.ПАСС</form:label>
                                    <form:input path="speed_chief_even_pass" value="${station.speed_chief_even_pass}"
                                                id="speed_chief_even_pass"
                                                cssClass="form-control" type="number"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <form:label path="speed_chief_even_freight">ГЛ.ПТ.ГРУЗ</form:label>
                                    <form:input path="speed_chief_even_freight"
                                                value="${station.speed_chief_even_freight}"
                                                id="speed_chief_even_freight"
                                                cssClass="form-control" type="number"/>
                                </div>
                            </div>

                        </div>
                    </fieldset>

                </div>
                <div class="col-md-6">
                    <fieldset>
                        <legend>Нечетный:</legend>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <form:label path="speed_chief_odd_pass">ГЛ.ПТ.ПАСС</form:label>
                                    <form:input path="speed_chief_odd_pass" id="speed_chief_odd_pass"
                                                cssClass="form-control" value="${station.speed_chief_odd_pass}"
                                                type="number"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <form:label path="speed_chief_odd_freight">ГЛ.ПТ.ГРУЗ</form:label>
                                    <form:input path="speed_chief_odd_freight"
                                                value="${station.speed_chief_odd_freight}" id="speed_chief_odd_freight"
                                                cssClass="form-control" type="number"/>
                                </div>
                            </div>

                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <fieldset>
                        <legend>Бокоые пути:</legend>
                        <div class="form-group">
                            <form:input path="branch_track" value="${station.branch_track}" id="branch_track"
                                        cssClass="form-control" type="number"/>
                        </div>
                    </fieldset>
                </div>
            </div>


        </div>
    </div>


    <div class="row">
        <div class="col-md-6">
            <c:if test="${!flagEdit}">
                <button type="submit" class="btn btn-default">Добавить</button>
            </c:if>
            <c:if test="${flagEdit}">
                <button type="submit" formaction="/admin/speed/editStation" class="btn btn-default">Изменить</button>
                <%--<button type="submit" formaction="/admin/speed/deleteStation" class="btn btn-default">Удалить</button>--%>
                <div class="form-group">
                    <input type="checkbox" name="sendMessage" value="true">Отослать сообщение?
                </div>
            </c:if>

            </form:form>
        </div>
        <div class="col-md-6">
            <div class="col-md-6">
                <fieldset>
                    <legend>ТРА:</legend>
                    <div>
                        <p id="pic" style="display: none">
                            <img src="../../../resources/images/load.gif" alt="">
                        </p>

                        <p id="img_tra_station">

                        </p>
                        <div id="modalImg">

                        </div>

                    </div>
                </fieldset>
            </div>
            <div class="col-md-6">
                <fieldset>
                    <legend>Добавить ТРА:</legend>
                    <div class="form-group">
                        <input type="file" id="btnAddImgStation">
                        <p id="notValid"></p>
                        <button id="addImgFile" disabled>Отправить файл</button>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>

<div>

</div>
</div>
<jsp:include page="../Footer.jsp"/>
