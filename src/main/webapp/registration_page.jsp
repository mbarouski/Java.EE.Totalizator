<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="styles/form_styles.css" />" />
    <script src="<c:url value="js/validation.js" />"></script>
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="header.jsp" %>
    <div class="main">
        <%@ include file="left_menu.jsp" %>
        <div class="center-part">
            <div class="registration">
                <form class="form" method="post" onsubmit="return validate();" action="main?command=Register">
                    <div class="reg-form">
                        <div class="label-div">
                            <label><fmt:message bundle="${loc}" key="label.name"/> </label>
                            <label>Пароль</label>
                            <label>Подтверждение пароля</label>
                            <label>E-mail</label>
                        </div>
                        <div class="input-div">
                            <input id="name-input" type="text" name="name" pattern="[a-zA-Z]{1}[a-zA-Z_0-9]{4,}" required />
                            <input id="pass-input" type="password" name="pass" required />
                            <input id="pass-confirm-input" type="password" name="pass-confirm" required />
                            <div class="email-div">
                                <input id="mail-input" type="email" name="mail1" required />
                            </div>
                        </div>
                        <div class="err-div">
                            <p class="hidden" id="name-err-p"><fmt:message bundle="${loc}" key="err.incorrectName" /></p>
                            <p class="hidden" id="pass-err-p"><fmt:message bundle="${loc}" key="err.incorrectPassword" /></p>
                            <p class="hidden" id="pass-confirm-err-p"><fmt:message bundle="${loc}" key="err.incorrectConfirmPassword" /></p>
                            <p class="hidden" id="mail-err-p"><fmt:message bundle="${loc}" key="err.incorrectEmail" /></p>
                        </div>
                    </div>
                    <div class="btn-container">
                        <button class="register-btn" type="submit"><fmt:message bundle="${loc}" key="link.registration" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>