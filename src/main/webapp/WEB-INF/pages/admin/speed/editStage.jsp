<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <form:form method="post" modelAttribute="stage">
        <div class="row">
            <div class="col-md-12"><h3 align="center">${stage.startStation} - ${stage.endStation}</h3></div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group" style="border: 1px solid #c0c0c0; border-radius: 5px;">
                    <h4 align="center">Нечётное направление</h4>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <fieldset>
                                    <legend>Пасс:</legend>
                                    <form:input path="speed_pass_odd" cssClass="form-control" id="speed_pass_odd"
                                                type="number"
                                                value="${stage.speed_pass_odd}"/>
                                </fieldset>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <fieldset>
                                    <legend>Груз:</legend>
                                    <form:input path="speed_freight_odd" cssClass="form-control" id="speed_freight_odd"
                                                type="number"
                                                value="${stage.speed_freight_odd}"/>

                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <fieldset>
                        <legend>Примечания</legend>
                        <form:textarea cssClass="form-control" value="${stage.notation_odd}" path="notation_odd"
                                       id="notation_odd" rows="5"></form:textarea>
                    </fieldset>
                    <div class="row">

                    </div>

                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group" style="border: 1px solid #c0c0c0; border-radius: 5px;">
                    <h4 align="center">Чётное направление</h4>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <fieldset>
                                    <legend>Пасс:</legend>
                                    <form:input path="speed_pass_even" cssClass="form-control" id="speed_pass_even"
                                                type="number"
                                                value="${stage.speed_pass_even}"/>
                                </fieldset>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <fieldset>
                                    <legend>Груз:</legend>
                                    <form:input path="speed_freight_even" cssClass="form-control"
                                                id="speed_freight_even"
                                                type="number"
                                                value="${stage.speed_freight_even}"/>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <fieldset>
                        <legend>Примечания</legend>
                        <form:textarea cssClass="form-control" value="${stage.notation_even}" path="notation_even"
                                       id="notation_even" rows="5"></form:textarea>
                    </fieldset>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-default">Изменить</button>
                <div class="form-group">
                    <input type="checkbox" name="sendMessage" value="true">Отослать сообщение?
                </div>
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="../Footer.jsp"/>