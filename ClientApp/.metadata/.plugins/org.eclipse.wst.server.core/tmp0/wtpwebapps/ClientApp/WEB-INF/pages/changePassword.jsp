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
    <title>Change password form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
   	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
   	
    <style>
        .container {
            max-width: 300px;
            margin-top: 100px;
            
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
            <button type="button" class="button-text" onclick="window.history.back()">
    			<i class="bi bi-arrow-left"></i> <!-- Bootstrap ikona -->
			</button>
        </div>
		<h2 class="mb-4">Change password</h2>
		<form method="post" action="Controller" onsubmit="return validatePasswords()">
			<input type="hidden" name="action" value="change">
			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
			</div>
			<div class="mb-3">
				<label for="repeatedPassword" class="form-label">Repeat password</label>
                <input type="password" id="repeatedPassword" name="repeatedPassword" class="form-control" required>
				<p id="passwordError" style="color: red; display: none;">Passwords don't match.</p>
				
			</div>
			
			<div class="mb-3">
				<button type="submit" class="btn btn-primary">Save</button>
			</div>
			
		</form>
	</div>
	
	<script>
    	function validatePasswords(){
    		const password = document.getElementById("password").value;
    		const repeatedPassword = document.getElementById("repeatedPassword").value;
    		
    		if(password !== repeatedPassword){
    			document.getElementById("passwordError").style.display = "block";
    			console.log("LOZINKE SE NE PODUDARAJU")
    			return false;
    		}
    		
            document.getElementById("passwordError").style.display = "none"; // Sakrijte poruku o grešci ako su lozinke iste

    		return true;
    	}
    
    </script>
	
	
</body>
</html>