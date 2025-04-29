<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Add Promotion</title>
    
    
    <style>
    	.form-promotion {
    		max-width: 600px;
    		margin: 0 auto;
    		padding: 20px;
    		border: 1px solid #ddd;
    		border-radius: 10px;
    		background-color: #f9f9f9;
    		margin-top: 50px;
    	}
    	
    	.form-promotion h2 {
    		text-align: center;
    		margin-bottom: 20px;
    	}
    	
    	.form-promotion .form-input {
    		width: 100%;
    		padding: 10px;
    		margin-bottom: 15px;
    		border: 1px solid #ccc;
    		border-radius: 5px;
    	}
    	
    	.form-promotion .btn {
    		width: 100%;
    		padding: 10px;
    		border-radius: 5px;
    	}
    	
    	.error-message {
    		color: red;
    		text-align: center;
    		margin-top: 10px;
    	}
    
    
    </style>
    
</head>
<body>

	<div class="container">
		<form class="form-promotion" id="promotionForm">
			<h2 class="mb-4">Add promotion</h2>
			
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<input type="text" id="title" name="title" class="form-input" required> 
			</div>
			
			<div class="mb-3">
				<label for="description" name="description" class="form-label">Description</label>
				<textarea id="description" name="description" class="form-input" required></textarea>
			</div>
			
			<div class="mb-3">
				<label for="startDate" class="form-label">Start date</label>
				<input type="date" id="startDate" name="startDate" class="form-input" required>
			</div>
			
			<div class="mb-3">
				<label for="endDate" class="form-label">End date</label>
				<input type="date" id="endDate" name="endDate" class="form-input" required>
			</div>
			
			<button class="btn btn-primary" type="button" onclick="savePromotion()">Save promotion</button>
			
			<div id="errorMessage" class="error-message" style="display: none"></div>
			
		
		</form>
	
	</div>
	
	
	
	<script>
		function savePromotion(){
			
			const title = document.getElementById("title").value;
			const description = document.getElementById("description").value;
			const startDate = document.getElementById("startDate").value;
			const endDate = document.getElementById("endDate").value;
			
			
			if(!title || !description || !startDate || !endDate){
				document.getElementById("errorMessage").innerText = "All fields are required!";
				document.getElementById("errorMessage").style.display = "block";
				return;
			}
			
			
			//kreiram objekat sa podacima
			const promotionData = {
				title: title,
				description: description,
				startDate: startDate,
				endDate: endDate
			};
			
			//saljem putem AJAX zahtjeva
			fetch("savePromotion.jsp", {
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify(promotionData)
			})
			.then(response => response.text())
			.then(data => {
				
				console.log(data);
				
				 if (data.trim() === "success") {  // trim() da uklonimo whitespace
				     window.location.href = "mainPage.jsp";
				 } else {
				     document.getElementById("errorMessage").innerText = "Failed to save promotion!";
				     document.getElementById("errorMessage").style.display = "block";
				 }
			})
			.catch(error => {
				console.error("Error: ", error);
				document.getElementById("errorMessage").innerText = "An error occurred!";
				document.getElementById("errorMessage").style.display = "block";
			});
			
		}
	
	</script>

	
</body>
</html>