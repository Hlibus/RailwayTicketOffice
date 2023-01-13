<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
</head>
<body>

<%@include file="view/jspf/header.jspf"%>
<hr>

<div class="searchTools">
    <form>
        <ul class="searchTravelInput">
            <datalist id="stations">
                <c:forEach items="${stations}" var="station">
                    <option value="${station.getName()}"></option>
                </c:forEach>
            </datalist>
            <li><a>Choose 2 city to travel</a></li>
            <li><input name="stationFrom" list="stations" placeholder="from"></li>
            <li><input name="stationWhere" list="stations" placeholder="where"></li>
            <li><button type="submit">Get</button></li>
        </ul>
    </form>
</div><hr>

<h2>Available travels</h2>
<c:choose>
    <c:when test="${travels != null}">
        <table>
            <tr>
                <th>Route Name</th>
                <th>Train Number</th>
                <th>Departure DateTime</th>
                <th></th>
            </tr>
            <c:forEach var="travel" items="${travels}">
                <tr>
                    <td>${travel.getRouteName()}</td>
                    <td>${travel.getTrainNumber()}</td>
                    <td>${travel.getDepartureDateTime()}</td>
                    <td><a href="getTravelPage?travelId=${travel.getId()}">select</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>Sorry, in this moment no active travels</h2>
    </c:otherwise>
</c:choose>

</body>
</html>