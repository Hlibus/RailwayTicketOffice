<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Edit travel</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>

<c:choose>
    <c:when test="${selectedTravel != null}">
        <br><h3>Selected travel: ${selectedTravel}</h3>
        <form>
            <p>Edit route:
                <select name="routeSelect">
                    <option>${selectedTravelRoute}</option>
                    <c:choose>
                        <c:when test="${routes != null}">
                            <c:forEach var="routes" items="route">
                                <option value="${route}"></option>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </select></p>

            <p>Edit train:
                <select name="trainSelect">
                    <option>${selectedTravelTrain}</option>
                    <c:choose>
                        <c:when test="${trains != null}">
                            <c:forEach var="trains" items="train">
                                <option value="${train}"></option>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </select></p>

            <p>Choose date and time of departure:
                <input type="datetime-local" value="${selectedTravelDateTime}">
            </p>

            <input type="submit" value="Edit">
        </form>
    </c:when>
</c:choose>

</body>
</html>
