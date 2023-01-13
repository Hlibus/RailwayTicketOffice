<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Travel page</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h3>Travel info</h3>
<c:choose>
    <c:when test="${travelWithNames != null}">
        <h4 >Route name - ${travelWithNames.getRouteName()}</h4>
        <h4>Train number - ${travelWithNames.getTrainNumber()}</h4>
        <h4>Departure date and time - ${travelWithNames.getStringDepartureDateTime()}</h4>
    </c:when>
    <c:otherwise>
        <h4>Something went wrong, can`t find travel</h4>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${trainWithInfoDto != null}">
        <h4>Count of wagons - ${trainWithInfoDto.getWagonsCount()}</h4>
        <h4>Count of all seats - ${trainWithInfoDto.getSeatsCount()}</h4>
    </c:when>
    <c:otherwise>
        <h4>Something went wrong, can`t find train</h4>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${routeStationWithNames != null}">
        <h4>Loose places - ${LoosePlaces}</h4>
        <h4>General price - ${travelGeneralPrice}</h4>
        <h4>General time in travel - ${travelGeneralTime}</h4>
        <h4>Arrival time - ${travelArrivalTime}</h4>

        <hr>
        <c:choose>
            <c:when test="${roleId == 1}">
                <h3>Select stations to get ticket</h3>
                <form action="getTicketBuyingPage" method="get">
                    <input name="travelId" value="${travelWithNames.getId()}" hidden readonly>
                    <select name="stationFromId" required>
                        <option></option>
                        <c:forEach items="${routeStationWithNames}" var="routeStation">
                            <option  value="${routeStation.getStationFromId()}">${routeStation.getStationFromName()}</option>
                        </c:forEach>
                    </select>
                    <select name="stationWhereId" required>
                        <option></option>
                        <c:forEach items="${routeStationWithNames}" var="routeStation">
                            <option value="${routeStation.getStationWhereId()}">${routeStation.getStationWhereName()}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="buy">
                </form>
            </c:when>
            <c:otherwise>
                <h3>For buying ticket need to be authorized</h3>
            </c:otherwise>
        </c:choose>

        <hr>
        <h3>Travel stations</h3>
        <table>
            <tr>
                <th>From</th>
                <th>From-ADDRESS</th>
                <th>Where</th>
                <th>Where-ADDRESS</th>
                <th>Price</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${routeStationWithNames}" var="routeStation">
                <tr>
                    <td>${routeStation.getStationFromName()}</td>
                    <td>${routeStation.getStationFromAddress()}</td>
                    <td>${routeStation.getStationWhereName()}</td>
                    <td>${routeStation.getStationWhereAddress()}</td>
                    <td>${routeStation.getPrice()}</td>
                    <td>${routeStation.getTravelTime()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h4>Something went wrong, can`t find train</h4>
    </c:otherwise>
</c:choose>

</body>
</html>
