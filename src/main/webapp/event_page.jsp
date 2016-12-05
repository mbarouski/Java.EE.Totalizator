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
    <%@ include file="header.jsp" %>
    <div class="main">
        <%@ include file="left_menu.jsp" %>
        <div class="center-part">
            <h4>${event.eventName}</h4>
            <p>${event.eventLeague}</p>
            <hr>
            <c:if test="${!empty event.liveTranslationLink}">
                <iframe width="420" height="315" src="${event.liveTranslationLink}" frameborder="0" allowfullscreen />
            </c:if>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>