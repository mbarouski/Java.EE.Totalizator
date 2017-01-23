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
                <form class="form" method="post" action="main?command=withdrawMoney">
                    <div class="reg-form">
                        <div class="header-div">
                            <p class="form-header"><fmt:message bundle="${loc}" key="link.withdraw"/></p>
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
                            <label><fmt:message bundle="${loc}" key="label.card-number"/></label>
                            <input placeholder="XXXXXXXXXXXXXXXX" type="text" name="card-number" pattern="[0-9]{16}" required
                                   value="<c:out value="${operation.cardNumber}"/>" title="<fmt:message bundle="${loc}" key="help.card-number"/>"/>
                        </div>
                        <div class="input-div">
                            <label><fmt:message bundle="${loc}" key="label.validity-period"/></label>
                            <input placeholder="XX/XX" type="text" name="validity-date" pattern="[0-9]{2}/[0-9]{2}" required
                                   value="<c:out value="${operation.validityDate}"/>"/>
                        </div>
                        <div class="input-div">
                            <label><fmt:message bundle="${loc}" key="label.amount"/></label>
                            <input placeholder="XXX.XX" type="text" name="amount" pattern="[0-9]{1,3}\.[0-9]{1,2}" required
                                   value="<c:out value="${operation.amount}"/>" title="<fmt:message bundle="${loc}" key="help.amount"/>"/>
                        </div>
                        <div class="btn-container">
                            <button class="register-btn" type="submit"><fmt:message bundle="${loc}" key="link.withdraw" /></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>