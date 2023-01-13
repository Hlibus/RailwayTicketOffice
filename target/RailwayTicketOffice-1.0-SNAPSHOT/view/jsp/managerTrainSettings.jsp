<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<script>
    <%@ include file="/view/js/trainSettingsSelectionScript.js"%>
</script>
<html>
<head>
    <title>Train settings</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>
<hr>

<h2>Train settings</h2>
<hr>

<h3>Add new train</h3>
<form action="addTrain" method="post">
    <p>Number of train:
        <input name="trainNumber" type="text" placeholder="number" required></p>
    <p>Wagons and seats:</p>
    <textarea id="countWagonsAndSeatsForUser" readonly draggable="false" required></textarea>
    <textarea id="countWagonsAndSeats" name="countWagonsAndSeats" readonly hidden draggable="false"></textarea>

    <br><input type="submit" value="Add new train">
</form>
<h4>Add train wagons</h4>

<p>Count of wagons:
    <input id="wagonsCount" type="number" placeholder="count wagons" required></p>
<p>Count of seats in wagons:
    <input id="seatsCount" type="number" placeholder="count seats" required></p>
<button onclick="addCountsToTextarea()">Add to wagons</button>

<hr>
<h3>Trains</h3>
<c:choose>
    <c:when test="${trains != null}">
        <table >
            <tr>
                <th>Number</th>
                <th>Wagons</th>
                <th>Seats</th>
<%--                <th>Edit</th>--%>
                <th>Delete</th>
            </tr>
            <c:forEach var="train" items="${trains}">
                <tr>
                    <td>${train.getNumber()}</td>
                    <td>${train.getWagonsCount()}</td>
                    <td>${train.getSeatsCount()}</td>
<%--                    <td><a href="">edit</a></td>--%>
                    <td>
                        <form action="deleteTrain" method="post">
                            <input name="trainId" type="number" hidden readonly value="${train.getId()}">
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>

</body>
</html>
