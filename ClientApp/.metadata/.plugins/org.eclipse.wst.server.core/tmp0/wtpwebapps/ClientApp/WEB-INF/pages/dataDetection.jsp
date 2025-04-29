<%@page import="beans.ClientBean"%>
<%@page import="dto.Vehicle"%>
<%@page import="java.util.List"%>

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
	
	<title>DataDetection</title>
	
	
	<style>
		
	
		.container {
			max-width: 500px;
			margin-top: 120px;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			text-align: center;
			position: relative;
			background: #d1f542;
		}
		
		h2 {
			padding-top: 15px;
		}
		
		
		
		.corner-element {
			position: absolute;
			top: 10px;
			left: 10px;
			border: 2px solid black;
			border-radius: 10px;
		}
		
		.back-button{
			position: absolute;
			top: 0;
			left: 0;
			background: none;
			padding: 0;
		}
		
			.button-text {
    		all: unset; /* Resetuje sve defaultne stilove dugmeta */
    		color: #080710;
    		background: rgb(139, 202, 12);
    		padding: 10px 10px;
    		border-radius: 5px;
    		font-weight: bold;
		    text-align: center;
		    cursor: pointer;
		    width: calc(100% - 24px);
		    /* padding-top: 10px;
		    padding-bottom: 10px; */
    
		}
		
		.btn {
			margin-top: 20px;
		}
	
	</style>
	
	
	
	
</head>
<body>

	 <!-- Modalni prozor za poruku da klijent ne moze iznajmiti auto ako nema vozacku -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="errorModalLabel">Error</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Problem with vehicle rental!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>





	<div class="container">
	
		<div class="corner-element">
			<button type="button" class="button-text" onclick="window.location.href='Controller?action=mainPage'">
				<i class="bi bi-arrow-left"></i>
			</button>
		</div>
		
		<h2 class="mb-4">Rental informations</h2>
		
		<div class="mb-3">
    		<label for="location" class="form-label">Location</label>
    		<input type="text" id="location" name="location" class="form-control w-100" disabled required>
		</div>
		<div class="mb-3">
			<label for="vehicles" class="form-label">Vehicles</label>
			<select id="vehicles" name="vehicles" class="form-control w-100">
				<% for(Vehicle v : (List<Vehicle>) request.getAttribute("vehicles") ){ %>
					<option value=<%=v.getIdVehicle() %> required> <%= v.getManufacturer() %> <%= '-'  %> <%= v.getModel() %> </option>
				
				<% } %>
			
			</select>
		</div>
		
		<div class="mb-3">
			<label for="cardNumber" class="form-label">Card number</label>
			<input type="text" id="cardNumber" name="cardNumber" class="form-control w-100" required
				minlength="16" maxlength="16" pattern="[0-9]{16}" 
                title="Card Number must be exactly 16 numbers."
                oninput="validateCardNumber(this)"
			>
			<span id="errorMessage" style="color:red; display:none;">Card number must be exactly 16 digits!</span>
			
		</div>
		
		
		<div class="mb-3">
			<a href="#" id="startRentButton" class="btn btn-primary">Start</a>
		</div>
		
	</div>
	
	
	
	
	
	
	<script>
		
	
	function getLocation() {
	    if (navigator.geolocation) {
	        console.log("Geolocation is supported by this browser.");
	        
	        navigator.geolocation.getCurrentPosition(
	            (position) => {
	                console.log("Location retrieved successfully:", position);

	                // Proveravamo eksplicitno da li postoje latitude i longitude
	                if (position.coords && typeof position.coords.latitude === "number" && typeof position.coords.longitude === "number") {
	                    const lat = position.coords.latitude.toFixed(6);  // Ograničavamo broj decimala
	                    const lon = position.coords.longitude.toFixed(6);

	                    console.log(`Latitude: ${lat}, Longitude: ${lon}`);

	                    const locationInput = document.getElementById("location");
	                    
	                    locationInput.value = position.coords.latitude + ", " + position.coords.longitude;
	                    
	                    fetch('/ClientApp/Controller', {
	                    	method: 'POST',
	                    	headers: {
	                    		'Content-Type': 'application/x-www-form-urlencoded',
	                    	},
	                    	body: 'action=saveLocation&latitude=' + encodeURIComponent(lat) + '&longitude=' + encodeURIComponent(lon)
	                    });
	                    
	                } else {
	                    console.error("Latitude or longitude is not valid!");
	                    alert("Could not retrieve valid coordinates. Please try again.");
	                }
	            },
	            (error) => {
	                console.error("Error getting location:", error);
	                alert("Unable to retrieve your location! Please allow location access.");
	            }
	        );
	    } else {
	        console.error("Geolocation is not supported by this browser.");
	        alert("Geolocation is not supported by this browser!");
	    }
	}

	window.onload = getLocation;
	
	
	
	
	function validateCardNumber(input) {
        // Uklanja sve karaktere koji nisu cifre
        input.value = input.value.replace(/\D/g, '');

        // Ograničava unos na 16 cifara
        if (input.value.length > 16) {
            input.value = input.value.slice(0, 16);
        }
    }
	
	
	document.getElementById('startRentButton').addEventListener('click', function(event){
	    const cardNumber = document.getElementById('cardNumber').value;
	    const selectedVehicleId = document.getElementById('vehicles').value;

	    console.log("Selected Vehicle ID:", selectedVehicleId); // Provjera vrijednosti

	    if(cardNumber.length < 16){
	        document.getElementById('errorMessage').style.display = 'block';
	        event.preventDefault();
	    } else {
	        document.getElementById('errorMessage').style.display = 'none';

	        // Slanje podataka na server koristeći fetch API
	        fetch('/ClientApp/Controller', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded',
	            },
	            body: 'action=startRent&vehicleId=' + encodeURIComponent(selectedVehicleId) // Koristite jednostruke navodnike i konkatenaciju
	        })
	        .then(() => {
	            console.log('Data sent successfully');
	            window.location.href = 'Controller?action=rentalPeriod'; // Preusmjeri nakon slanja
	        })
	        .catch((error) => {
	            console.error('Error: ', error);
	        });
	    }
	});
	

	</script>
	
	
	
	
</body>
</html>