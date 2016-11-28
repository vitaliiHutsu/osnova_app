<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <fieldset>
                <legend>Информация:</legend>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>П.№</th>
                        <th width="50%">Сообщение</th>
                        <th>Дата</th>
                        <th>Удалить</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${listInformation}" var="inform" varStatus="index">
                        <tr>
                            <td>
                                    ${index.index}
                            </td>
                            <td>
                                    ${inform.action}
                            </td>
                            <jsp:useBean id="date_info" class="java.util.Date"/>
                            <jsp:setProperty name="date_info" property="time" value="${inform.date_add}"/>
                            <td>
                                <fmt:formatDate value="${date_info}" pattern="dd-MM-yyyy HH:mm"/>
                            </td>
                            <td><a href="/admin/deleteInformation/${inform.id}">удалить</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </fieldset>

        </div>

        <div class="col-md-6">
            <fieldset>
                <legend>Новая информация:</legend>

                <form:form modelAttribute="newInform" method="post" action="/admin/newMessage">
                    <form:label path="action">Сообщение:</form:label>
                    <form:textarea path="action" cssClass="form-control" id="action" rows="4" maxlength="500"/>

                    <button type="submit" class="btn btn-default">
                        Добавить
                    </button>
                </form:form>


            </fieldset>

        </div>
    </div>
</div>
<jsp:include page="Footer.jsp"/>
