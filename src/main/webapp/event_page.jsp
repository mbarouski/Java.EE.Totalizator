<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="<c:url value="styles/event.css" />" rel="stylesheet" />
    <link href="<c:url value="styles/make-rate.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <title>Totalizator</title>
</head>
<body>
<%@ include file="parts/make_rate_form.jsp" %>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>
        <div class="center-part">
            <div class="event-block">
                <div class="event-header">
                    <div class="event-info">
                        <h5 class="event-name"><c:out value="${event.eventName}" /></h5>
                        <p class="event-league">${event.eventLeague}</p>
                        <time>Дата начала: ${event.eventDate} ${event.eventTime}</time>
                    </div>
                    <c:if test="${event.status eq 'FINISHED'}">
                        <div class="event-result">
                            <h6>Results</h6>
                        </div>
                    </c:if>
                </div>
                <div class="event-live">
                    <c:if test="${!empty event.liveTranslationLink}">
                        <iframe width="640" height="480" src="${event.liveTranslationLink}" frameborder="0" allowfullscreen>
                        </iframe>
                    </c:if>
                </div>
                <div class="event-members">
                    <c:forEach var="member" items="${event.members}">
                        <h6>${member}</h6>
                    </c:forEach>
                </div>
                <c:choose>
                    <c:when test="${!empty sessionScope.username}">
                        <input type="checkbox" id="enabler" class="open">
                        <script>
                            function togggle(){
                                var cb = document.getElementById("enabler");
                                cb.checked = !cb.checked;
                            }
                        </script>
                        <button onclick="togggle(); return false;" class="make-rate-btn"><label for="enabler">Сделать ставку</label></button>
                    </c:when>
                    <c:when test="${empty sessionScope.username}">
                        <div class="register-warn-div">
                            <p>Please, register or log in to make rate.</p>
                        </div>
                    </c:when>
                </c:choose>

            </div>
        </div>
    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>