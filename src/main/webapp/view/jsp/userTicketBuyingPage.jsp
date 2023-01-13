<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Buying ticket</title>
</head>
<body>

<header>
    <%@ include file="/view/jspf/header.jspf"%>
</header>

<div class="travelTicket">
    <h2>Buying ticket</h2>
    <form action="buyTicket" method="post">
        <input name="travelId" type="hidden" value="${ticketFullInfo.getTravelId()}">
        <h4>Route name - ${ticketFullInfo.getRouteName()}</h4>
        <input name="stationFromId" type="hidden" value="${ticketFullInfo.getStationFromId()}">
        <h4>Station from - ${ticketFullInfo.getStationFromName()}</h4>
        <input name="stationWhereId" type="hidden" value="${ticketFullInfo.getStationWhereId()}">
        <h4>Station where - ${ticketFullInfo.getStationWhereName()}</h4>
        <input name="trainId" type="hidden" value="${ticketFullInfo.getTrainId()}">
        <h4>Train number - ${ticketFullInfo.getTrainNumber()}</h4>
        <h4>Departure date and time - ${ticketFullInfo.getDepartureDateTime()}</h4>
        <h4>Arrival date and time - ${ticketFullInfo.getArrivalDateTime()}</h4>
        <h4>Time in travel - ${ticketFullInfo.getTimeInTravel()}</h4>
        <h4>Price - ${ticketFullInfo.getPrice()}</h4>

    <%--    somehow select free positions--%>
<%--        <hr>--%>
<%--            <h3>Select places</h3>--%>
<%--            <h4>Wagon number - ${ss}</h4>--%>
<%--            <h4>Seat number - ${ss}</h4>--%>
<%--            <button>Select place</button>--%>
<%--        <hr>--%>
        <input type="submit" value="Buy">
    </form>
</div>

</body>
</html>
