function addToSelectedStationsInfo() {
    var stationFromSelectElement = document.getElementById("stationFromSelect");
    var stationWhereSelectElement = document.getElementById("stationWhereSelect");
    var betweenStationsPriceElement = document.getElementById("betweenStationsPrice");
    var betweenStationsTimeElement = document.getElementById("betweenStationsTime");

    var selectedStationsInfoElement = document.getElementById("selectedStationsInfo");
    var selectedStationsInfoWithIdElement = document.getElementById("selectedStationsInfoWithId");

    //VALUE AND ID OF SELECTED STATION
    var stationFrom = stationFromSelectElement.value;
    var optionsFrom = stationFromSelectElement.options;//NEED TO GET ID
    var stationFromId = optionsFrom[optionsFrom.selectedIndex].id;

    //VALUE AND ID OF SELECTED STATION
    var stationWhere = stationWhereSelectElement.value;
    var optionsWhere = stationWhereSelectElement.options;//NEED TO GET ID
    var stationWhereId = optionsWhere[optionsWhere.selectedIndex].id;

    var price = betweenStationsPriceElement.value;
    var time = betweenStationsTimeElement.value;

    if (stationFrom == null || stationWhere == null || price == null || time == null) {
        return;
    }

    stationFromSelectElement.value = stationWhere;
    stationWhereSelectElement.value = null;
    betweenStationsPriceElement.value = null;
    betweenStationsTimeElement.value = null;

    selectedStationsInfoElement.rows += 1;
    selectedStationsInfoElement.value += stationFrom + " " + stationWhere + " " + price + " " + time + "\n";
    selectedStationsInfoWithIdElement.value += stationFromId + " " + stationWhereId + " " + price + " " + time + "\n";
}