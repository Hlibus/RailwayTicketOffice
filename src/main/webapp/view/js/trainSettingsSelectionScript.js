function addCountsToTextarea() {
    var wagonsCountElement = document.getElementById("wagonsCount");
    var seatsCountElement = document.getElementById("seatsCount");
    var countWagonsAndSeatsForUserElement = document.getElementById("countWagonsAndSeatsForUser");
    var countWagonsAndSeatsElement = document.getElementById("countWagonsAndSeats");

    var wagonsCount = wagonsCountElement.value;
    var seatsCount = seatsCountElement.value;
    wagonsCountElement.value = null;
    seatsCountElement.value = null;

    countWagonsAndSeatsForUserElement.value += "Wagons: " + wagonsCount + " seats: " + seatsCount + "\n";
    countWagonsAndSeatsElement.value += wagonsCount + " " + seatsCount + "\n";
}