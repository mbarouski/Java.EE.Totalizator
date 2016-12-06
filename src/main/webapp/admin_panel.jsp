<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sport.totalizator.entity.User.Role" %>
<c:if test="${sessionScope.role == 'ADMINISTRATOR'}">
    <div class="admin-panel">
        <a href="<c:url value="main?command=showAddEventPage" />"><fmt:message bundle="${loc}" key="link.addEvent" /></a>
    </div>
</c:if>