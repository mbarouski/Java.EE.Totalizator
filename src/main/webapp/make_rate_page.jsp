<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="styles/form.css" />" />
    <script src="<c:url value="js/script_for_make_rate.js" />"></script>
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>

        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" onsubmit="" action="main?command=makeRate" charset="UTF-8">
                    <div class="header-div">
                        <p class="form-header"><fmt:message bundle="${loc}" key="link.make-rate"/></p>
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
                        <input id="event-id-input" type="text" name="event-id" required value="<c:out value="${eventId}"/>" hidden/>
                    </div>
                    <div class="input-div">
                        <label>Rate type</label>
                        <select id="rate-type-select" name="rate-type" size="1" onchange="onRateTypeSelectChange()">
                            <option selected value=""><fmt:message bundle="${loc}" key="label.choose-rate-type" /></option>
                            <option value="WIN"><fmt:message bundle="${loc}" key="WIN" /></option>
                            <option value="DRAW"><fmt:message bundle="${loc}" key="DRAW" /></option>
                            <option value="EXACT_SCORE"><fmt:message bundle="${loc}" key="EXACT_SCORE" /></option>
                        </select>
                    </div>
                    <div id="changable-part" class="changable-part">

                    </div>
                    <div class="input-div">
                        <label><fmt:message bundle="${loc}" key="label.money" /></label>
                        <input placeholder="XXX.XX" type="text" name="money" pattern="[0-9]{1,3}\.[0-9]{1,2}" required
                               value="<c:out value="${rate.sum}"/>"/>
                    </div>
                    <div class="btn-container">
                        <button class="submit-btn" type="submit"><fmt:message bundle="${loc}" key="link.make-rate"/></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>
