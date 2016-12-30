<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="styles/form.css" />" />
    <script src="<c:url value="js/validation.js" />"></script>
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" onsubmit="return validate();" action="main?command=login">
                    <div class="reg-form">
                        <c:if test="${!empty error}">
                            <div class="error-div">
                                    ${error}
                            </div>
                        </c:if>
                        <div class="input-div">
                            <label><fmt:message bundle="${loc}" key="label.name"/> </label>
                            <input id="name-input" type="text" name="login" pattern="[a-zA-Z]{1}[a-zA-Z_0-9]{4,}" required 
                                value="<c:out value="${user.login}"/>"/>
                        </div>
                        <div class="input-div">
                            <label><fmt:message bundle="${loc}" key="label.password"/></label>
                            <input id="pass-input" type="password" name="password" required />
                        </div>
                        <div class="btn-container">
                            <button class="register-btn" type="submit"><fmt:message bundle="${loc}" key="link.login" /></button>
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