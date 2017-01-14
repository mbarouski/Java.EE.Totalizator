<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
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
                                <th>Отметить</th>
                                <th>Имя</th>
                                <th>Роль</th>
                                <th>Блокировка</th>
                            </thead>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td><input type="checkbox" id="${user.userId}" onchange="onCheckboxClick(this)"/></td>
                                    <td><p>${user.login}</p></td>
                                    <td><p>${user.role}</p></td>
                                    <td><p>${user.banned}</p></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="input-div">
                        <button class="admin-btn" onclick="return false;" onsubmit="return false;">Сменить роль на</button>
                        <select name="role">
                            <option selected value="USER">USER</option>
                            <option selected value="MODERATOR">MODERATOR</option>
                            <option selected value="ADMINISTRATOR">ADMINISTRATOR</option>
                        </select>
                    </div>
                    <div class="input-div">
                        <button class="admin-btn" onclick="ban()">Заблокировать</button>
                    </div>
                    <div class="input-div">
                        <button class="admin-btn" onclick="unban()">Разблокировать</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
</html>

