<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<header>
    <div class="logo-panel">
        <div class="bg-dark rounded">
            <img class="logo" src="<c:url value="images/logo.png" />" alt="logo" />
        </div>
        <div class="bg-dark">
            <form class="search">
                <input type="text" name="what-search" />
                <button class="search-btn" type="submit"><fmt:message bundle="${loc}" key="btn.search" /></button>
            </form>
        </div>
    </div>
    <div class="menu">
        <nav class="main-menu">
            <div class="main-menu-item-container">
                <a class="active" href="<c:url value="main" />"><span><fmt:message bundle="${loc}" key="link.main" /></span></a>
                <a href="<c:url value="main?command=showResultsPage" />"><span><fmt:message bundle="${loc}" key="link.results" /></span></a>
                <a href="<c:url value="main?command=showPersonalPage" />"><span><fmt:message bundle="${loc}" key="link.personalPage" /></span></a>
            </div>
            <div class="login-menu-item-container">
                <c:if test="${empty sessionScope.username}">
                    <a class="login-menu-item" href="<c:url value="main?command=showLoginPage" />"><fmt:message bundle="${loc}" key="link.login" /></a>
                    <a class="login-menu-item" href="<c:url value="main?command=showRegistrationPage" />"><fmt:message bundle="${loc}" key="link.registration" /></a>
                </c:if>
                <c:if test="${!empty sessionScope.username}">
                    <a class="login-menu-item" href="<c:url value="main?command=logout" />"><fmt:message bundle="${loc}" key="link.logout" />(${sessionScope.username})</a>
                </c:if>
            </div>
        </nav>
    </div>
</header>
