<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Tickets page</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h2>Tickets</h2>
<div>
    <h3>Actual tickets</h3>
    <c:choose>
        <c:when test="${actualTickets != null}">
            <c:forEach items="actualTickets" var="actualTicket">
                <div class="card">
                    <h4></h4>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</div>

<%--<div>--%>
<%--    <h3>Old tickets</h3>--%>
<%--    <c:choose>--%>
<%--        <c:when test="">--%>
<%--            <c:forEach items="" var="">--%>

<%--            </c:forEach>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>

<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
<%--</div>--%>

</body>
</html>
