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
    
    <style>
        body {
            background-color: #f7cf02;
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
            padding: 20px; 
            position: relative;  
        }
        
        .corner-element {
        	position: absolute;
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
	
		/* Smanjujemo veličinu slike da bude u skladu sa dizajnom */
		.back-button img {
		    width: 30px;
		    height: 30px;
		}
		
        
        .btn.btn-primary {
        	width: 20vw;
        }
        
    </style>
    <title>Profile</title>
</head>
<body>
    <div class="container">
    	<div class="corner-element">
    	<!-- onclick="window.location.href='Controller?action=profileView'" - vraca na specificnu stranicu -->
            <button type="button" class="button-text" onclick="window.location.href='Controller?action=mainPage'">
    			<i class="bi bi-arrow-left"></i> <!-- Bootstrap ikona -->
			</button>
        </div>
    	
        <h2 class="mb-4">Profile</h2>
        
        
        
        <div class="row">
            <div class="col-md-6">
               	<div class="mb-3">
    				<a href="Controller?action=changePassword" class="btn btn-primary">Change password</a>
				</div>
				<div class="mb-3">
    				<a href="Controller?action=deactivate" class="btn btn-primary">Deactivate account</a>
				</div>
				<div class="mb-3">
    				<a href="Controller?action=rentals" class="btn btn-primary">My rentals</a>
				</div>
				<div class="mb-3">
    				<a href="Controller?action=logout" class="btn btn-primary">Logout</a>
				</div>
				
            </div>
        </div>
    </div>
</body>
</html>