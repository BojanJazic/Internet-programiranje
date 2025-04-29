<%@page import="beans.ClientBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<%-- <%
		  if(session.getAttribute("clientBean") == null || !((ClientBean) session.getAttribute("clientBean")).isLoggedIn()) {
			  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/login.jsp");
			  dispatcher.forward(request, response);
		      return; // Prekida dalje izvršavanje JSP stranice
		  }
	%> --%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
    
    
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .form-label {
            font-weight: bold;
        }
        .alert {
            margin-top: 20px;
        }
        
        .corner-element {
        	position: fixed;
    		top: 10px;   
    		left: 10px;
    		background-color: rgb(139, 202, 12);
    		border: 2px solid black;
    		border-radius: 10px;
        }
        
        .back-button {
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
        
        
        
    </style>
</head>
<body>
    <div class="container">
    
    	<div class="corner-element">
    		<!-- onclick="window.location.href='Controller?action=profileView'" - vraca na specificnu stranicu -->
            <button type="button" class="button-text" onclick="window.location.href='Controller?action=profileView'">
    			<i class="bi bi-arrow-left"></i> <!-- Bootstrap ikona -->
			</button>
        </div>
    
        <h2 class="mb-4">Registration</h2>
        <form class="form-registration" method="post" action="Controller">
            <input type="hidden" name="action" value="register">

            <!-- Name -->
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <!-- Surname -->
            <div class="mb-3">
                <label for="surname" class="form-label">Surname</label>
                <input type="text" id="surname" name="surname" class="form-control" required>
            </div>

            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>

            <!-- Phone -->
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" id="phone" name="phone" class="form-control" required>
            </div>

            <!-- Avatar (File Upload) -->
            <div class="mb-3">
                <label for="avatar" class="form-label">Avatar (Profile Picture)</label>
                <input type="file" id="avatar" name="avatar" class="form-control" accept="image/*">
            </div>

            <!-- ID Card -->
            <div class="mb-3">
                <label for="id-card" class="form-label">ID Card Number</label>
                <input type="text" id="id-card" name="id-card" class="form-control" required
                       minlength="8" maxlength="8" pattern="[A-Za-z0-9]{8}" 
                       title="ID Card Number must be exactly 8 alphanumeric characters.">
            </div>

            <!-- Passport (Optional) -->
            <div class="mb-3">
                <label for="passport" class="form-label">Passport Number</label>
                 <input type="text" id="passport" name="passport" class="form-control"
                       minlength="8" maxlength="8" pattern="[A-Za-z0-9]{8}" 
                       title="Passport Number must be exactly 8 alphanumeric characters.">
            </div>

            <!-- Driver License Number (Optional) -->
            <div class="mb-3">
                <label for="driver-license" class="form-label">Driver License Number</label>
                 <input type="text" id="driver-license" name="driver-license" class="form-control"
                       minlength="8" maxlength="8" pattern="[A-Za-z0-9]{8}" 
                       title="Driver License Number must be exactly 8 alphanumeric characters.">
            </div>

            <!-- Submit Button -->
            <button class="btn btn-primary" type="submit">Register</button>
        </form>

        <!-- Notification for Errors -->
         <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger mt-4" role="alert">
                Registration failed. Please check your input and try again.
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>