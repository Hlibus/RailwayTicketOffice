<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<script>
    <%@ include file="/view/js/routeSettingsSelectionsScript.js"%>
</script>
<html>
<head>
    <title>Route settings</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h2>Route settings</h2>
<hr>

<h3>Add new route</h3>
<form action="addNewRoute" method="post">
    <p>Route name:
        <input name="routeName" id="routeName" type="text" placeholder="name" required></p>

    <h4>Stations select</h4>

    <p>Select station from
    <select id="stationFromSelect" required>
        <option></option>
        <c:choose>
            <c:when test="${stations != null}">
                <c:forEach var="station" items="${stations}">
                    <option value="${station.getName()}" id="${station.getId()}">${station.getName()}</option>
                </c:forEach>
            </c:when>
        </c:choose>
    </select></p>

    <p>Select station where
    <select id="stationWhereSelect">
        <option></option>
        <c:choose>
            <c:when test="${stations != null}">
                <c:forEach var="station" items="${stations}">
                    <option value="${station.getName()}" id="${station.getId()}" >${station.getName()}</option>
                </c:forEach>
            </c:when>
        </c:choose>
    </select></p>

    <p>Price between stations
        <input id="betweenStationsPrice" type="number" step="0.01" placeholder="price"></p>

    <p>Travel time between stations
        <input id="betweenStationsTime" type="time"></p>

    <button onclick="addToSelectedStationsInfo()" type="button">Add to route</button><br><br>

    <p>Result of select:</p>
    <textarea  id="selectedStationsInfo" style="width: 200px" readonly draggable="true"></textarea>
    <textarea name="selectedStationsInfoWithId" id="selectedStationsInfoWithId" readonly hidden></textarea>

    <br><input type="submit" value="Add new route">
</form>
<hr>

<h3>Routes</h3>
<c:choose>
    <c:when test="${routes != null}">
        <table>
            <tr>
                <th>Name</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.getName()}</td>
                    <td><a href="getRouteEdit?routeId=${route.getId()}">edit</a></td>
                    <td>
                        <form action="deleteRoute" method="post">
                            <input name="routeId" value="${route.getId()}" hidden>
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h4>Routes are not created</h4>
    </c:otherwise>
</c:choose>

</body>
</html>
