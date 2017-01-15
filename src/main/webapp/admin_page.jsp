<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="styles/form.css" />" />
    <link rel="stylesheet" href="<c:url value="styles/admin.css" />" />
    <script src="js/admin.js"></script>
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" charset="UTF-8" method="post" action="main?command=showAdminPage" onsubmit="return false;">
                    <div class="input-div">
                        <table class="user-table">
                            <thead>
                                <th><fmt:message bundle="${loc}" key="label.mark" /></th>
                                <th><fmt:message bundle="${loc}" key="label.username" /></th>
                                <th><fmt:message bundle="${loc}" key="label.role" /></th>
                                <th><fmt:message bundle="${loc}" key="label.block" /></th>
                            </thead>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td><input type="checkbox" name="checkbox" id="${user.userId}" onchange="onCheckboxClick(this)"/></td>
                                    <td><p>${user.login}</p></td>
                                    <td><p id="role-p">${user.role}</p></td>
                                    <td><p id="banned-p">${user.banned}</p></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="input-div">
                        <button class="admin-btn" onclick="changeRole()"><fmt:message bundle="${loc}" key="label.change-role-on" /></button>
                        <select id="role" name="role">
                            <option selected value="USER">USER</option>
                            <option selected value="MODERATOR">MODERATOR</option>
                            <option selected value="ADMINISTRATOR">ADMINISTRATOR</option>
                        </select>
                    </div>
                    <div class="input-div">
                        <button class="admin-btn" onclick="ban()"><fmt:message bundle="${loc}" key="label.ban" /></button>
                        <button class="admin-btn" onclick="unban()"><fmt:message bundle="${loc}" key="label.unban" /></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>

