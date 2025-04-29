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
    	.form-post {
    		max-width: 600px;
    		margin: 0 auto;
    		padding: 20px;
    		border: 1px solid #ddd;
    		border-radius: 10px;
    		background-color: #f9f9f9;
    		margin-top: 100px;
    	}
    	
    	.form-post h2 {
    		text-align: center;
    		margin-bottom: 20px;
    	}
    	
    	.form-post .form-input {
    		width: 100%;
    		padding: 10px;
    		margin-bottom: 15px;
    		border: 1px solid #ccc;
    		border-radius: 5px;
    	}
    	
    	.form-post .btn {
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
		<form class="form-post" id="formPost">
			<h2 class="mb-4">Add post</h2>
			
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<input type="text" id="title" name="title" class="form-input" required>
			</div>
			
			<div class="mb-3">
				<label for="content" class="form-label">Content</label>
				<textarea id="content" name="content" class="form-input" required></textarea>
			</div>
			
			<button class="btn btn-primary" type="button" onclick="savePost()">Save post</button>
			
			<div id="errorMessage" class="error-message" style="display: none"></div>
			
		
		</form>
	
	</div>
	
	<script>
	
		function savePost(){
			const title = document.getElementById("title").value;
			const content = document.getElementById("content").value;
			
			if(!title || !content){
				document.getElementById("errorMessage").innerText = "All fields are required!";
				document.getElementById("errorMessage").style.display = "block";
				return;
			}
			
			const postData = {
				title: title,
				content: content
			};
			
			
			fetch("savePost.jsp", {
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify(postData)
				
			})
			.then(response => response.text())
			.then(data => {
				if(data.trim() === "success"){
					window.location.href = "mainPage.jsp";
				} else {
					document.getElementById("errorMessage").innerText = "Failed to save post!";
					document.getElementById("errorMessage").style.display = "block";
				}
			})
			.catch(error => {
				document.getElementById("errorMessage").innerText = "An error occurred!";
				document.getElementById("errorMessage").style.display = "block";
			});
		}
	
	
	</script>
	
	
</body>
</html>