<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${events.pageCount > 1}">
    <div class="pagination-div">
        <ul class="pagination">
            <c:forEach var="page" begin="1" end="${events.pageCount}" >
                <c:choose>
                    <c:when test="${page == events.page}">
                        <li><a href="#" class="current-page" >${page}</a></li>
                    </c:when>
                    <c:when test="${page != events.page}">
                        <c:choose>
                            <c:when test="${categoryPage}">
                                <li><a href="<c:url value="main?command=${command}&page=${page}&categoryId=${categoryId}"/>">${page}</a></li>
                            </c:when>
                            <c:when test="true">
                                <li><a href="<c:url value="main?command=${command}&page=${page}"/>">${page}</a></li>
                            </c:when>
                        </c:choose>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</c:if>
