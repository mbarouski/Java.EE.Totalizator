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
            <div class="registration" style="width: 800px;">
                <form class="form" method="post" onsubmit="return validate();" action="main?command=addEvent">
                    <div class="reg-form">
                        <div class="label-div">
                            <label>Название</label>
                            <label>Ссылка на трансляцию</label>
                            <label>Категория</label>
                            <label>Лига</label>
                            <label>Дата</label>
                        </div>
                        <div class="input-div">
                            <input type="text" name="name" required />
                            <input type="text" name="liveTranslation" />
                            <div class="checkbox-div">
                                <div class="checkbox"><label>Выигрыш</label><div><input type="checkbox" name="winRate" value="WIN"></div></div>
                                <div class="checkbox"><label>Проигрыш</label><div><input type="checkbox" name="drawRate" value="DRAW"></div></div>
                                <div class="checkbox"><label>Первый гол</label><div><input type="checkbox" name="firstGoalRate" value="FIRST_GOAL"></div></div>
                                <div class="checkbox"><label>Точный счёт</label><div><input type="checkbox" name="exactScoreRate" value="EXACT_SCORE"></div></div>
                            </div>
                            <select name="category-id" required>
                                <option selected>Выберите категорию</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>

                            <select name="league-id" required disabled>
                                <option selected>Выберите лигу</option>
                                <c:forEach var="league" items="${leagues}">
                                    <option value="${league.id}">${league.name}</option>
                                </c:forEach>
                            </select>

                            <input type="datetime" min="2016-01-01 00:00:00" max="2020-12-31 00:00:00" id="myDate">
                        </div>
                    </div>
                    <div class="btn-container">
                        <button class="register-btn" type="submit">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
