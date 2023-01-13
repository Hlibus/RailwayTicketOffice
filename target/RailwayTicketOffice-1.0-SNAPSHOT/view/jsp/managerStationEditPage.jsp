<%@ include file="/view/jspf/page.jspf" %>
<%@ include file="/view/jspf/taglib.jspf" %>
<html>
<head>
    <title>Station edit</title>
</head>
<body>
<%@ include file="/view/jspf/header.jspf"%>

<h3>Edit station</h3>
<form action="editStation" method="post">
    <input hidden name="id" value="${station.getId()}">
    <p>Station name*
        <input name="stationName" type="text" placeholder="name" value="${station.getName()}" required></p>
    <p>Address*
        <input name="stationAddress" type="text" placeholder="address" value="${station.getAddress()}" required></p>
    <p>Coordinates
        <input name="stationCoordinates" type="text" placeholder="coordinates" value="${station.getCoordinates()}"></p>
    <input type="submit" value="Edit">
</form>

</body>
</html>
