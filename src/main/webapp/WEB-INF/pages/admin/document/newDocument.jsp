<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<style>
    .error {
        color: red;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12"><h3 style="text-transform: uppercase" align="center">${category.title}</h3></div>
    </div>
    <div class="row">
        <form:form method="post" modelAttribute="document" enctype="multipart/form-data" id="formDocument">
            <div class="col-md-3">
                <div class="form-group">
                    <form:errors class="error" path="number_doc" display="block"/><br>
                    <form:label path="number_doc">Номер документа:</form:label>
                    <form:input path="number_doc" cssClass="form-control" id="number_doc" type="text"
                                value="${document.number_doc}" name="number_doc"/>
                </div>

                <div class="form-group">
                    <form:errors class="error" path="date_document" display="block"/><br>
                    <form:label path="date_document">Дата документа:</form:label>
                    <form:input path="date_document" cssClass="form-control" id="date_document" type="text"
                                value="${document.date_document}" name="date_document"/>
                </div>

                <div class="form-group">
                    <label for="inputFile">Файл:</label>
                    <input name="file" type="file" id="inputFile">
                    <c:if test="${not empty errorMassagePdf}"><p class="help-block error">${errorMassagePdf}</p></c:if>
                    <c:if test="${empty errorMassagePdf}"><p class="help-block">Добавлять файл только PDF
                        формата</p></c:if>

                </div>

                <br>

                <button type="submit" class="btn btn-default">
                    Добавить
                </button>
            </div>

            <div class="col-md-9">
                <div class="form-group">
                    <form:errors class="error" path="title" display="block"/><br>
                    <form:label path="title">Название документа:</form:label>
                    <form:textarea class="form-control" value="${document.title}"
                                   path="title" id="title" rows="4"
                                   type="text"/>
                </div>

            </div>


        </form:form>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>
