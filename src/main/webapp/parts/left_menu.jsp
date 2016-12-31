<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="left-menu">
    <nav>
        <a href="<c:url value="main?command=showNearestEventsPage" />"><fmt:message bundle="${loc}" key="link.nearest" /></a>
        <b><fmt:message bundle="${loc}" key="label.categories" /></b>
        <c:forEach var="category" items="${categories}">
            <a href="<c:url value="main?command=showCategoryPage&categoryId=${category.id}" />" >${category.name}</a>
        </c:forEach>
    </nav>
</div>