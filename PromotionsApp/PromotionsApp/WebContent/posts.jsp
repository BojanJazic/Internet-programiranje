<%@page import="beans.PostBean"%>
<%@page import="java.util.List"%>
<%@page import="service.PostManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
   
	<title>Posts</title>
	
	<style>
    	.container {
    		max-width: 100vw;
    		margin-top: 60px;
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
		
		tr {
			justify-content: center;
			text-align: center;
		}
		
		td {
			justify-content: center;
			text-align: center;
		}
		
		.thead-dark {
			background-color: rgb(139, 202, 12);
		}
	
		
    </style>
	
	
	
</head>
<body>


	<div class="container">
	
		<div class="corner-element">
    		<!-- onclick="window.location.href='Controller?action=profileView'" - vraca na specificnu stranicu -->
            <button type="button" class="button-text" onclick="window.history.back()">
    			<i class="bi bi-arrow-left"></i> <!-- Bootstrap ikona -->
			</button>
        </div>
    
    	<table class="table table-striped">
		<thead class="thead-dark">
			 <tr>
	            <th>Title</th>
	            <th>Content</th>
	            
	           
        	</tr>
		</thead>
		<tbody>
			 <%
			 	PostManager postManager = new PostManager();
			 	List<PostBean> posts = postManager.getPosts();
			 	
			 	for(PostBean post : posts){
			 %>
			 	<tr>
			 		<td><%= post.getTitle() %></td>
			 		<td><%= post.getContent() %></td>
			 		
			 	</tr>
			 <%} %>
		</tbody> 
	</table>
        
    </div>



</body>
</html>