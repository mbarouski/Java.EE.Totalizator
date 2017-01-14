<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="styles/form.css" />" />
    <script src="<c:url value="js/load_data.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="styles/jquery/jquery.datetimepicker.css" />"/>
    <title>Totalizator</title>
</head>
<body>
<div class="container">
    <%@ include file="parts/header.jsp" %>
    <div class="main">
        <%@ include file="parts/left_menu.jsp" %>

        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" onsubmit="" action="main?command=addEvent" charset="UTF-8">
                    <div class="header-div">
                        <p class="form-header"><fmt:message bundle="${loc}" key="link.addEvent"/></p>
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
                        <label>Название</label>
                        <input type="text" name="name" required />
                    </div>
                    <div class="input-div">
                        <label>Ссылка на трансляцию</label>
                        <input type="text" name="liveTranslation" />
                    </div>
                    <div class="input-div">
                        <label>Категория</label>
                        <select id="category-select" name="category-id" onchange="setLeaguesSelect()" required>
                            <option value="0" selected>Выберите категорию</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-div">
                        <label>Лига</label>
                        <select id="league-select" name="league-id" onchange="setMembersSelect()" required >
                            <option selected>Выберите лигу</option>
                            <c:forEach var="league" items="${leagues}">
                                <option value="${league.id}">${league.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-div">
                        <label>Дата</label>
                        <input type="text" value="" id="datetimepicker" name="date"/>
                    </div>
                    <div class="input-div">
                        <label>Типы ставок</label>
                        <div class="checkbox-div">
                        <div class="checkbox">
                            <label>Выигрыш</label>
                            <div><input type="checkbox" name="winRate" value="WIN" /></div>
                        </div>
                        <div class="checkbox">
                            <label>Ничья</label>
                            <div><input type="checkbox" name="drawRate" value="DRAW"></div>
                        </div>
                        <%--<div class="checkbox">--%>
                            <%--<label>Первый гол</label>--%>
                            <%--<div><input type="checkbox" name="firstGoalRate" value="FIRST_GOAL"></div>--%>
                        <%--</div>--%>
                        <div class="checkbox">
                            <label>Точный счёт</label>
                            <div><input type="checkbox" name="exactScoreRate" value="EXACT_SCORE"></div>
                        </div>
                    </div>
                    </div>
                    <div class="input-div">
                        <label>Участники</label>
                        <div class="member-div">
                            <select id="member-select" name="member-select-1" required >
                                <option selected>Выберите участника</option>
                                <c:forEach var="member" items="${members}">
                                    <option value="${member.id}">${member.name}</option>
                                </c:forEach>
                            </select>
                            <select id="member-select" name="member-select-2" required >
                                <option selected>Выберите участника</option>
                                <c:forEach var="member" items="${members}">
                                    <option value="${member.id}">${member.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--<div class="add-member-select-btn-div">--%>
                        <%--<button onclick="addMemberSelect(); return false;">Добавить участника</button>--%>
                    <%--</div>--%>
                    <div class="btn-container">
                        <button class="submit-btn" type="submit">Добавить событие</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%@ include file="parts/footer.jsp" %>
</div>
</body>
<script src="<c:url value="js/jquery/jquery.js" />"></script>
<script src="<c:url value="js/jquery/jquery.datetimepicker.full.js" />"></script>
<script>
    $('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});
</script>
</html>
