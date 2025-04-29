<%@page import="beans.ClientBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<%
		  if(session.getAttribute("clientBean") == null || !((ClientBean) session.getAttribute("clientBean")).isLoggedIn()) {
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
			  dispatcher.forward(request, response);
		      return; // Prekida dalje izvršavanje JSP stranice
		  }
	%>


<html>
<head>
	<meta charset="UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<title>RentalPeriod</title>

	<style>
		
		.container {
			max-width: 300px;
			margin-top: 200px;
			background: #d1f542;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
		
		input {
			text-align: center;
		
		}
		
	</style>



</head>
<body>

	 <%
        // Pročitaj cijenu iz session atributa
        Integer vehiclePrice = (Integer) session.getAttribute("vehiclePrice");
        if (vehiclePrice == null) {
            vehiclePrice = 10; // Default vrijednost ako atribut ne postoji
        }
    %>
    

	<div class="container">
		<div class="mb-3">
			<label class="form-label">Amount</label>
			<input type="number" id="amount" class="form-control" disabled>
		</div>
		<div class="mb-3">
			<label class="form-label">Time</label>
			<input type="text" id="time" class="form-control" disabled>
		</div>
		<div class="mb-3">
			<button id="finishButton" class="btn btn-primary">Finish</button>
		</div>
		
	</div>
		
	 <!-- Skriveni input za pohranu cijene -->
    <input type="hidden" id="vehiclePrice" value="<%= vehiclePrice %>">
    <input type="hidden" id="idVehicle" value="<%= session.getAttribute("idVehicle") %>">
    <input type="hidden" id="latitude" value="<%= session.getAttribute("latitude") %>">
	<input type="hidden" id="longitude" value="<%= session.getAttribute("longitude") %>">
    
	
	
	<script>
    const idVehicle = document.getElementById('idVehicle').value;
    
    const latitudeInput = document.getElementById('latitude');
    const longitudeInput = document.getElementById('longitude');

    let latitude = parseFloat(latitudeInput.value) || 44.7866; // Default vrednost (Beograd)
    let longitude = parseFloat(longitudeInput.value) || 20.4489;

    const vehiclePrice = parseInt(document.getElementById('vehiclePrice').value, 10);
    console.log("Vehicle Price:", vehiclePrice);

    const amountInput = document.getElementById('amount');
    amountInput.value = vehiclePrice; // Postavi početnu vrednost na vehiclePrice

    let startTime = 0;
    const timeInput = document.getElementById("time");

    // Vremenski korak za testiranje: 30 minuta (1800 sekundi)
    const timeStep = 1800;
    let isFirstHour = true;

    function updateTime() {
        const hours = Math.floor(startTime / 3600);
        const minutes = Math.floor((startTime % 3600) / 60);
        const seconds = startTime % 60;

        const formattedTime =
            String(hours).padStart(2, '0') + ':' +
            String(minutes).padStart(2, '0') + ':' +
            String(seconds).padStart(2, '0');

        timeInput.value = formattedTime;
        startTime += timeStep;

        // Ažuriraj koordinate (povećanje za 1 metar)
        updateCoordinates(1000);

        if (startTime % 3600 === 0 && startTime > 0) {
            console.log("Prelazak u novi sat! startTime:", startTime);

            if (!isFirstHour) {
                calculateAmount();
            } else {
                isFirstHour = false;
            }
        }
    }

    function updateCoordinates(distanceMeters) {
    	
    	console.log("Distanca primljena:", distanceMeters); // Debug
    	
        const earthRadius = 6378137; // Poluprečnik Zemlje u metrima
        const pi = Math.PI;

        const deltaLat = (distanceMeters / earthRadius) * (180 / pi);
        const deltaLon = (distanceMeters / earthRadius) * (180 / pi) / Math.cos(latitude * (pi / 180));

        latitude += deltaLat;
        longitude += deltaLon;

        console.log('Pomeraj: ${distanceMeters}m | Nova Lat: ${latitude}, Nova Lon: ${longitude}');

        latitudeInput.value = latitude.toFixed(8);
        longitudeInput.value = longitude.toFixed(8);
    }


    function calculateAmount() {
        console.log("Pozvan calculateAmount!");
        const currentAmount = parseInt(amountInput.value, 10) || 0;
        const newAmount = currentAmount + vehiclePrice;
        console.log(`Novi iznos: ${newAmount}`);
        amountInput.value = newAmount;
    }

    document.getElementById('finishButton').addEventListener('click', function() {
        const rentingDuration = Math.ceil(startTime / 3600);
        const dropoffLocation = `${latitude}, ${longitude}`;
        const pickupLocation = `${latitude}, ${longitude}`;
        const amount = amountInput.value;

        const rentalData = {
            id_vehicle: idVehicle,
            renting_duration: rentingDuration,
            pickup_location: pickupLocation,
            dropoff_location: dropoffLocation,
            amount: amount
        };

        console.log("Podaci za slanje:", rentalData);

        fetch('/ClientApp/Controller?action=finishRental', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(rentalData),
        })
        .then(response => {
            if (response.ok) {
                window.location.href = "/ClientApp/Controller?action=mainPage";
            } else {
                alert("Failed to finish rental!");
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Failed to finish rental!");
        });
    });

    setInterval(updateTime, 1000); // Simulacija kretanja svake sekunde
</script>

	
	
	

</body>
</html>