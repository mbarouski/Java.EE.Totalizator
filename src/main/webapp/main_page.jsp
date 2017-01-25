<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="http://totalizator.by/tag/myTags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value="styles/styles.css" />" rel="stylesheet" />
	<link href="<c:url value="styles/pagination.css"/>" rel="stylesheet" />
	<title>Totalizator</title>
</head>
<body>
	<div class="container">
		<%@ include file="parts/header.jsp" %>
		<div class="main">
			<%@ include file="parts/left_menu.jsp" %>
			<div class="center-part">
				<%@ include file="parts/admin_panel.jsp" %>
				<c:forEach var="event" items="${events.elementList}">
					<div class="event">
						<my:event-info-tag eventName="${event.eventName}" eventLeague="${event.eventLeague}"
										   eventTime="${event.eventTime}" eventDate="${event.eventDate}"></my:event-info-tag>
						<div class="event-secondary">
							<a href="<c:url value="main?command=showEventPage&eventId=${event.eventId}" />"><button class="event-btn"><fmt:message bundle="${loc}" key="btn.open" /></button></a>
						</div>
					</div>

				</c:forEach>
				<%@ include file="parts/pagination.jsp" %>
			</div>
		</div>
		<%@ include file="parts/footer.jsp" %>
	</div>
</body>
</html>