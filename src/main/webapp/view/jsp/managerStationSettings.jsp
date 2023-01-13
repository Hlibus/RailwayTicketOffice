<%@ include file="/view/jspf/page.jspf" %>
<%@ include file="/view/jspf/taglib.jspf" %>
<html>
<head>
    <title>Station settings</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h2>Station settings</h2>
<hr>

<%--<c:choose>--%>
<%--    <c:when test="${roleId == 2}">--%>
<h3>Add new station</h3>
<form action="addNewStation" method="post">
    <p>Station name*
    <input name="stationName" type="text" placeholder="name" required></p>
    <p>Address*
    <input name="stationAddress" type="text" placeholder="address" required></p>
    <p>Coordinates
    <input name="stationCoordinates" type="text" placeholder="coordinates"></p>
    <input type="submit" value="Add new station">
</form>
<%--    </c:when>--%>
<%--</c:choose>--%>
<hr>
<h3>Stations</h3>
<c:choose>
    <c:when test="${stations != null}">
        <table >
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Edit</th>
                <th>Delete</th>
<%--                <th>Coordinates</th>--%>
                <th></th>
            </tr>

            <c:forEach var="station" items="${stations}">
                <tr>
                    <td>${station.getName()}</td>
                    <td>${station.getAddress()}</td>
<%--                    <td>${station.getCoordinates()}</td>--%>
                    <td><a href="getStationEdit?stationId=${station.getId()}">edit</a></td>
<%--                    <td><a href="deleteStation?stationId=${station.getId()}">delete</a></td>--%>
                    <td>
                        <form action="deleteStation" method="post">
                            <input name="stationId" value="${station.getId()}" hidden>
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
                <h4 value="${station}"></h4><a href="stationEdit?${station}">
            </c:forEach>
        </table>
    </c:when>
</c:choose>

</body>
</html>
