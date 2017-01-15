<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${events.pageCount > 1}">
    <div class="pagination-div">
        <ul class="pagination">
            <c:forEach var="page" begin="1" end="${events.pageCount}" >
                <c:choose>
                    <c:when test="${page == events.page}">
                        <li><a class="current-page" href="<c:url value="main?command=${command}&page=${page}"/>">${page}</a></li>
                    </c:when>
                    <c:when test="${page != events.page}">
                        <li><a href="<c:url value="main?command=${command}&page=${page}"/>">${page}</a></li>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</c:if>
