<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="styles/form.css" />" />
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>

        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" onsubmit="" action="main?command=addEventResult" charset="UTF-8">
                    <div class="header-div">
                        <p class="form-header"><fmt:message bundle="${loc}" key="link.addEventResult"/></p>
                    </div>
                    <c:if test="${!empty success}">
                        <div class="success-div">
                                ${success}
                        </div>
                    </c:if>
                    <c:if test="${!empty error}">
                        <div class="error-div">
                                ${error}
                        </div>
                    </c:if>
                    <div class="input-div">
                        <input type="text" name="event-id" required value="<c:out value="${eventId}"/>" hidden/>
                    </div>
                    <div class="input-div">
                        <label><fmt:message bundle="${loc}" key="label.choose-winner"/></label>
                        <select name="winner-id">
                            <option selected><fmt:message bundle="${loc}" key="label.choose-member"/></option>
                            <c:forEach var="member" items="${members}">
                                <option value="${member.id}">${member.name}</option>
                            </c:forEach>
                        </select>
                        <input name="winner-score" type="text" pattern="[0-9]+"/>
                    </div>
                    <div class="input-div">
                        <label><fmt:message bundle="${loc}" key="label.choose-loser"/></label>
                        <select name="loser-id">
                            <option selected><fmt:message bundle="${loc}" key="label.choose-member"/></option>
                            <c:forEach var="member" items="${members}">
                                <option value="${member.id}">${member.name}</option>
                            </c:forEach>
                        </select>
                        <input name="loser-score" type="text" pattern="[0-9]+"/>
                    </div>
                    <div class="btn-container">
                        <button style="width: 300px;" class="submit-btn" type="submit"><fmt:message bundle="${loc}" key="link.addEventResult"/></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>
