<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
    <title>Travel settings</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h2>Travel settings</h2>
<hr>

<h3>Add new travel</h3>
<form action="addNewTravel" method="post">
    <p>Select route:
        <select name="routeId" required>
            <option></option>
            <c:choose>
                <c:when test="${routes != null}">
                    <c:forEach items="${routes}" var="route">
                        <option value="${route.getId()}">${route.getName()}</option>
                    </c:forEach>
                </c:when>
            </c:choose>
        </select></p>

    <p>Select train:
    <select name="trainId" required>
        <option></option>
        <c:choose>
            <c:when test="${trains != null}">
                <c:forEach items="${trains}" var="train">
                    <option value="${train.getId()}">${train.getNumber()}</option>
                </c:forEach>
            </c:when>
        </c:choose>
    </select></p>

    <p>Choose date and time of departure:
        <input name="departureDate" type="datetime-local" min="${minTravelDateTime}" required>
    </p>

    <input type="submit" value="Add new travel">
</form><br>
<hr>

<h3>Travels</h3><br>
<c:choose>
    <c:when test="${travelWithNames != null && travelWithNames.size() != 0}">
        <table>
            <tr>
                <th>Route</th>
                <th>Train</th>
                <th>DateTime</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${travelWithNames}" var="travel">
            <tr>
                <td>${travel.getRouteName()}</td>
                <td>${travel.getTrainNumber()}</td>
                <td>${travel.getDepartureDateTime()}</td>
                <td><a href="travelEdit?${travel.getId()}">edit</a></td>
                <td>
                    <form action="deleteTravel" method="post">
                        <input name="travelId" value="${travel.getId()}" hidden readonly>
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h4>No actual travels</h4>
    </c:otherwise>
</c:choose>

</body>
</html>
