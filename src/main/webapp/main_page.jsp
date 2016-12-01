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
						<a class="active" href="#"><span>Главная</span></a>
						<a href="#"><span>События</span></a>
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
					<a href="#">Ближайшие</a>
					<a href="#">Интересные</a>
					<b>Категории</b>
					<a href="#">Футбол</a>
					<a href="#">Хоккей</a>
					<a href="#">Баскетбол</a>
					<a href="#">Бадминтон</a>
					<a href="#">Гандбол</a>
				</nav>
			</div>
			<div class="center-part">
				<c:forEach var="event" items="${events}">
					<div class="event">
						<div class="event-main">
							<h5 class="event-name"><c:out value="${event.eventName}" /></h5>
							<p class="event-league">${event.leaugeName}</p>
							<time class="event-date">${event.eventDate}</time>
						</div>
						<div class="event-secondary">
							<p>Ставок: ${event.rateCount}</p>
							<button class="event-btn">Перейти</button>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
		<footer>
			<p>&copy; Maksim Barouski "Делай деньги" 2016</p>
		</footer>
	</div>
</body>
</html>