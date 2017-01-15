<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />

    <link href="<c:url value="styles/personal-page.css" />" rel="stylesheet" />
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>
        <div class="center-part">
            <div class="personal-page-div">
                <div class="personal-page-header">
                    <div class="id-info-div">
                        <h3>${user.login}</h3>
                        <p class="label">${user.email}</p>
                        <p class="label">${sessionScope.role}</p>
                    </div>
                    <div class="balance-div">
                        <div class="balance-h4-div">
                            <h4>${user.balance}$</h4>
                        </div>
                        <a class="balance-btn" href="<c:url value="main?command=showWithdrawMoneyPage"/>"><fmt:message bundle="${loc}" key="link.withdraw" /></a>
                        <a class="balance-btn" href="<c:url value="main?command=showFillUpBalancePage"/>"><fmt:message bundle="${loc}" key="link.fill-up" /></a>
                    </div>
                </div>
                <div class="active-rates-div">
                    <hr>
                    <h4><fmt:message bundle="${loc}" key="label.active-rates" /></h4>
                    <table border="1">
                        <tr class="table-header">
                            <th><fmt:message bundle="${loc}" key="label.event" /></th>
                            <th><fmt:message bundle="${loc}" key="label.amount" /></th>
                            <th><fmt:message bundle="${loc}" key="label.rate-type" /></th>
                        </tr>
                        <c:forEach var="rate" items="${user.activeRates}">
                            <tr>
                                <td><a href="<c:url value="main?command=showEventPage&eventId=${rate.eventId}"/>">${rate.eventName}</a></td>
                                <td>${rate.sum}</td>
                                <td><fmt:message bundle="${loc}" key="${rate.type}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <hr>
                <div class="finished-rates-div">
                    <hr>
                    <h4><fmt:message bundle="${loc}" key="label.finished-rates" /></h4>
                    <table border="1">
                        <tr class="table-header">
                            <th><fmt:message bundle="${loc}" key="label.event" /></th>
                            <th><fmt:message bundle="${loc}" key="label.amount" /></th>
                            <th><fmt:message bundle="${loc}" key="label.rate-type" /></th>
                            <th><fmt:message bundle="${loc}" key="label.win-amount" /></th>
                        </tr>
                        <c:forEach var="rate" items="${user.finishedRates}">
                            <tr>
                                <td><a href="<c:url value="main?command=showEventPage&eventId=${rate.eventId}"/>">${rate.eventName}</a></td>
                                <td>${rate.sum}</td>
                                <td><fmt:message bundle="${loc}" key="${rate.type}"/></td>
                                <td>${rate.win}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>
