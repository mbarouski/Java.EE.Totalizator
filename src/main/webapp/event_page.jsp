<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>
        <div class="center-part">
            <h5 class="event-name"><c:out value="${event.eventName}" /></h5>
            <p class="event-league">${event.eventLeague}</p>
            <c:if test="${!empty event.liveTranslationLink}">
                <hr>
                    <iframe width="420" height="315" src="${event.liveTranslationLink}" frameborder="0" allowfullscreen>
                    </iframe>
                <hr>
            </c:if>
            <time class="event-date">${event.eventDate} ${event.eventTime}</time>
        </div>
    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>