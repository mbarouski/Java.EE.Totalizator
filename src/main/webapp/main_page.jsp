<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
	<title>Totalizator</title>
</head>
<body>
	<div class="container"> 
		<header>
			<div class="logo-panel">
				<div class="bg-dark rounded">
					<img class="logo" src="<c:url value="images/logo.png" />" alt="logo" />
				</div>
				<div class="bg-dark">
					<form class="search">
						<input type="text" name="what-search" />
						<button class="search-btn" type="submit">Поиск</button>
					</form>
				</div>
			</div>
			<div class="menu">
				<nav class="main-menu">
					<div class="main-menu-item-container">
						<a class="active" href="<c:url value="main" />"><span>Главная</span></a>
						<a href="#"><span>Результаты</span></a>
						<a href="#"><span>Личная страница</span></a>
					</div>
					<a class="login-menu-item" href="#">Выйти(maximka777)</a>
				</nav>
			</div>
		</header>
		<div class="main">
			<div class="left-menu">
				<nav>
					<a href="<c:url value="main?command=showNearestEventsPage" />">Ближайшие</a>
					<a href="#">Интересные</a>
					<b>Категории</b>
					<c:forEach var="category" items="${categories}">
						<a href="<c:url value="main?command=showCategoryPage&categoryId=${category.id}" />" >${category.name}</a>
					</c:forEach>
				</nav>
			</div>
			<div class="center-part">
				<c:forEach var="event" items="${events}">
					<div class="event">
						<div class="event-main">
							<h5 class="event-name"><c:out value="${event.eventName}" /></h5>
							<p class="event-league">${event.eventLeague}</p>
							<time class="event-date">${event.eventDate} ${event.eventTime}</time>
						</div>
						<div class="event-secondary">
							<p>Ставок: ${event.rateCount}</p>
							<a href="<c:url value="main?command=showEventPage&eventId=${event.eventId}" />"><button class="event-btn">Перейти</button></a>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>