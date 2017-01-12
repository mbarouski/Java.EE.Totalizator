<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.role == 'MODERATOR'}">
    <div class="admin-panel">
        <a href="<c:url value="main?command=showAddEventPage" />"><fmt:message bundle="${loc}" key="link.addEvent" /></a>
        <a href="<c:url value="main?command=showAddCategoryPage" />"><fmt:message bundle="${loc}" key="link.addCategory" /></a>
        <a href="<c:url value="main?command=showAddLeaguePage" />"><fmt:message bundle="${loc}" key="link.addLeague" /></a>
        <a href="<c:url value="main?command=showAddMemberPage" />"><fmt:message bundle="${loc}" key="link.addMember" /></a>
    </div>
</c:if>