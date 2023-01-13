<%@ include file="/view/jspf/page.jspf" %>
<%@ include file="/view/jspf/taglib.jspf" %>
<html>
<head>
    <title>Route Edit</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<c:choose>
    <c:when test="${routesStations != null && routesStations.size() != 0}">
        <form action="editRoute" method="post">
            <input type="text" hidden readonly name="routeId" value="${routeId}">
            <input type="text" name="routeName" value="${routesStations.get(0).getRouteName()}">
            <input type="submit" value="Change name">
        </form>
        <table>
            <tr>
                <th>StationFrom</th>
                <th>From-Address</th>
                <th>StationWhere</th>
                <th>Where-Address</th>
                <th>Price</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${routesStations}" var="routeStations">
                <form action="editRouteStation" method="post">
                    <tr>
                        <input name="routeStationsId" value="${routeStations.getId()}" hidden readonly>
                        <input type="text" name="routeId" value="${routeId}" hidden readonly>
                        <input name="number" value="${routeStations.getNumber()}" hidden readonly>
                        <td>
                            <select name="stationFromName">
                                <option value="${routeStations.getStationFromName()}">${routeStations.getStationFromName()}</option>
                                <c:forEach items="${stations}" var="station">
                                    <option value="${station.getName()}">${station.getName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>${routeStations.getStationFromAddress()}</td>
                        <td>
                            <select name="stationWhereName">
                                <option value="${routeStations.getStationWhereName()}">${routeStations.getStationWhereName()}</option>
                                <c:forEach items="${stations}" var="station">
                                    <option value="${station.getName()}">${station.getName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>${routeStations.getStationWhereAddress()}</td>
                        <td>
                            <input name="price" type="number" value="${routeStations.getPrice()}">
                        </td>
                        <td>
                            <input name="time" type="time" value="${routeStations.getTravelTime()}">
                        </td>
                        <td>
                            <input type="submit" value="Edit">
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>

    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>


</body>
</html>
