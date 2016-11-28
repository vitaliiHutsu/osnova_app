<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/js/jquery-2.2.1.js" var="jquery"/>
<script src="${jquery}"></script>
<jsp:include page="../Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <h4 align="center">категории</h4>
                <c:forEach items="${categories}" var="category">
                    <a href="/document/category/${category.id}" class="list-group-item">${category.title}</a>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-7">
            <c:if test="${not empty docPresent}">
                <c:forEach items="${docPresent}" var="docPres">
                    <%--<a href="http://docs.google.com/gview?embedded=true&url=http://www.osnova.ml/document/viewDocument/${docPres.uuid}"--%>
                    <a href="/document/viewDocument/${docPres.uuid}"
                       style="text-decoration: none;">
                        <blockquote class="my-info">
                            <footer>${docPres.number_doc} ${docPres.date_document}</footer>
                            <p>${docPres.title}</p>
                        </blockquote>
                    </a>
                </c:forEach>
            </c:if>


            <c:if test="${not empty specificCategory}">
                <h4 align="center">${specificCategory.title}</h4>
            </c:if>
            <c:forEach items="${specificCategory.documents}" var="doc">
                <a href="/document/viewDocument/${doc.uuid}"
                <%--<a href="http://docs.google.com/gview?embedded=true&url=http://www.osnova.ml/document/viewDocument/${doc.uuid}"--%>
                style="text-decoration: none;">
                    <blockquote class="my-info">
                        <footer>${doc.number_doc} ${doc.date_document}</footer>
                        <p>${doc.title}</p>
                    </blockquote>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="../Footer.jsp"/>