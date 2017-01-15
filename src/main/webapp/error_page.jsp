<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href="<c:url value="styles/form.css" />" rel="stylesheet" />
</head>
<body>
    <div class="error-div">
        <h1>${message}</h1>
        <a href="<c:url value="main?command=showMainPage" />"><fmt:message bundle="${loc}" key="link.main-page" /></a>
    </div>
</body>
</html>
