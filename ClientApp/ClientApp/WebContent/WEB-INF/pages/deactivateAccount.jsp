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
	<title>DeactivateAccount</title>
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
 		
 		alert {
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
 		
 		.button-text{
 			all: unset; /* Resetuje sve defaultne stilove dugmeta */
    		color: #080710;
    		background: rgb(139, 202, 12);
    		padding: 10px 10px;
    		border-radius: 5px;
    		font-weight: bold;
		    text-align: center;
		    cursor: pointer;
		    width: calc(100% - 24px);
 			
 		}
 	
 	</style> 
 	
 	<script>
 		
 		
 		
 		async function validatePassword(){
 			console.log("validatePassword function called");
 			const password = document.getElementById("password").value;
 			console.log(password);
 			
 			
			<!-- slanje lozinke na server -->
			
			const response = await fetch("/ClientApp/Controller", {
				
				method: "POST",
				headers: {
	                "Content-Type": "application/x-www-form-urlencoded",
	            },
	            body: "action=checkPassword&password=" + encodeURIComponent(password),
			});
 			
			const result = await response.json();

			
			 if (result.success) {
		            // Ako je lozinka tačna, prikaži potvrdu za deaktivaciju
		         if (confirm("Are you sure you want to deactivate your account?")) {
		        	 // Pošalji formu za deaktivaciju
		             const form = document.createElement("form");
		             form.method = "POST";
		             form.action = "Controller";

		             const actionInput = document.createElement("input");
		             actionInput.type = "hidden";
		             actionInput.name = "action";
		             actionInput.value = "deactivate";
		             form.appendChild(actionInput);

		             document.body.appendChild(form);
		             form.submit();
					
		         }else {
		        	 // Ako korisnik klikne "Cancel", preusmjeri na profileView
		        	    window.location.href = "Controller?action=profileView";
		         }
		     } else {
		          // Ako je lozinka netačna, prikaži poruku o grešci
		          document.getElementById("passwordError").style.display = "block";
		     }
 			
 		}
 	
 	</script>
 	
  
</head>
<body>
	
	<div class="container">
    <div class="corner-element">
        <button type="button" class="button-text" onclick="window.history.back()">
            <i class="bi bi-arrow-left"></i> <!-- Bootstrap ikona -->
        </button>
    </div>

    <h2 class="mb-4">Deactivate account</h2>
	    <form id="deactivateForm">
	        <div class="mb-3">
	            <label for="password" class="form-label">Password</label>
	            <input type="password" id="password" name="password" class="form-control" required>
	            <p id="passwordError" style="color: red; display: none;">Incorrect password.</p>
	        </div>
	
	        <div class="mb-3">
	            <button type="button" class="btn btn-primary" onclick="validatePassword()">Deactivate</button>
	        </div>
	    </form>
	</div>
	
	
	
</body>
</html>