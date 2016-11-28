<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">

            <h3 align="center">Категория</h3>
            <select class="form-control" id="selectCategory" onchange="location = this.value;">
                <option value="">----</option>
                <c:forEach items="${categories}" var="category">
                    <option value="/admin/document/addNewDocument/${category.id}">
                            ${category.title}
                    </option>
                </c:forEach>
            </select>

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".addNewCategory">
                Добавить категорию
            </button>

            <div class="modal fade addNewCategory" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <form:form modelAttribute="newCategory" method="post">
                            <form:label path="title">Название категории:</form:label>
                            <form:input path="title" id="title"
                                        type="text" cssClass="form-control"/>
                            <button type="submit"
                                    formaction="/admin/document/addNewCategory"
                                    class="btn btn-default">Добавить
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <table class="table-bordered table">
                <thead>
                <tr>
                    <th>№ док.</th>
                    <th>название</th>
                    <th>категория</th>
                    <th>см.</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${documents}" var="document">
                    <tr>
                        <td width="25%">${document.number_doc}</td>
                        <td>${document.title}</td>
                        <td width="16%">${document.category.title}</td>
                        <td width="14%">
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target=".document-${document.id}">
                                Подробно
                            </button>
                        </td>
                    </tr>

                    <div class="modal fade document-${document.id}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                        <div class="modal-dialog modal-sm" role="document">
                            <div class="modal-content">
                                <address>
                                    <strong>Номер документа:</strong> <br>
                                    ${document.number_doc}
                                </address>
                                <address>
                                    <strong>Название документа:</strong> <br>
                                        ${document.title}
                                </address>
                                <address>
                                    <strong>Категория:</strong> <br>
                                        ${document.category.title}
                                </address>
                                <address>
                                    <strong>Дата документа:</strong> <br>
                                        ${document.date_document}
                                </address>
                                <address>
                                    <strong>Дата добавления документа:</strong> <br>
                                        <jsp:useBean id="documentDate" class="java.util.Date"/>
                                        <jsp:setProperty name="documentDate" property="time" value="${document.date_add}"/>
                                        <fmt:formatDate value="${documentDate}" pattern="dd-MM-yyyy"/>
                                </address>
                                <div class="row">
                                    <div class="col-md-6"><a href="/document/viewDocument/${document.uuid}">Смотреть</a></div>
                                    <%--<div class="col-md-6"><a href="http://docs.google.com/gview?embedded=true&url=http://www.osnova.ml/document/viewDocument/${document.uuid}">Смотреть</a></div>--%>
                                    <div class="col-md-6"><a href="/admin/document/delete/${document.id}" onclick="return confirm('Удалить документ?')">Удалить</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>

