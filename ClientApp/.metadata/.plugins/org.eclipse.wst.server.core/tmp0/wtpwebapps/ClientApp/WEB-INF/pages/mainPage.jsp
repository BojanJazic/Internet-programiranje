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
	<title>Main page</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<style>
		body{
			background-color: #f7cf02;
		}
		
		.btn.btn-primary{
			width: 30vw;
			heigh: 5vh;
		}
		
		.container {
			background: #d1f542;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			text-align: center;
			border-radius: 15px;
			max-width: 600px;
			margin-top: 200px;
		}
		
		.form-label {
			font-weight: bold;
		}
		.alert{
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
                    You don't have a driver license so you can't rent a car!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


	<div class="container">
		<h2 class="mb-4">Rentals</h2>
		<div class="mb-3">
			<a href="Controller?action=rentCar" class="btn btn-primary">Rent a car</a>
		</div>
		
		<div class="mb-3">
			<a href="Controller?action=rentBike" class="btn btn-primary">Rent a bike</a>
		</div>
		
		<div class="mb-3">
			<a href="Controller?action=rentScooter" class="btn btn-primary">Rent a scooter</a>
		</div>
		
		<div class="mb-3">
    		<a href="Controller?action=profileView" class="btn btn-primary">Profile</a>
		</div>
		
	<!--  	<button class="btn btn-primary" type="button">Rent a bike</button>
		<button class="btn btn-primary" type="button">Rent a scooter</button>
		<button class="btn btn-primary" type="button">Profile</button> -->
	
	</div>
	
	
	 <!-- Uključite Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	
	<script>
        // Prikaz modalnog prozora ako je showErrorModal postavljen na true
        <% if (request.getAttribute("showErrorModal") != null && (Boolean) request.getAttribute("showErrorModal")) { %>
            document.addEventListener('DOMContentLoaded', function() {
                const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
                errorModal.show(); // Prikazuje modalni prozor
            });
        <% } %>
    </script>
	
	
	
</body>
</html>