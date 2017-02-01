<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="Header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-7">
            <c:forEach items="${listInform}" var="inform">
                <blockquote class="my-info">
                    <footer>

                        <jsp:useBean id="date_info" class="java.util.Date"/>
                        <jsp:setProperty name="date_info" property="time" value="${inform.date_add}"/>
                        <fmt:formatDate value="${date_info}" pattern="dd-MM-yyyy HH:mm"/>

                        <cite title="Source Title">${inform.action}</cite></footer>
                </blockquote>
            </c:forEach>


        </div>

        <div class="col-md-4">
            <form method="post"  action="/sendMessage" class="my-info messageForm">
                <div class="form-group">
                    <label for="exampleInputEmail1">Тема сообщения:</label>
                    <input type="text" name="titleMessage" class="form-control" id="exampleInputEmail1" placeholder="Тема">
                </div>

                <div class="form-group">
                    <label for="textMessage">Сообщение:</label>
                    <textarea id="textMessage" name="textMessage" class="form-control" rows="3"></textarea>
                </div>

                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="Footer.jsp"/>
