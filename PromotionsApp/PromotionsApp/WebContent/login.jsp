<%@page import="beans.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
	
	<jsp:useBean id="userBean" class="beans.UserBean" scope="session"></jsp:useBean>    
	<jsp:useBean id="userManager" class="service.UserManager" scope="application"></jsp:useBean>

	<jsp:setProperty property="username" name="userBean" param="username"/>

<!DOCTYPE html>

<%
    boolean showError = false; // Dodajemo promenljivu za prikaz greške
    if(request.getParameter("submit") != null){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean user = userManager.login(username, password);
        
        System.out.println("USLO");
		
        if (user != null) {
        	System.out.println("USERNAME: " + user.getUsername());
            session.setAttribute("user", user);
            response.sendRedirect("mainPage.jsp");
            return;
        } else {
            showError = true; // Postavljamo da se prikaže poruka o grešci
        }
    }
    
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Login</title>
    <style>
        .form-login {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f9f9f9;
            margin-top: 100px;
        }

        .form-login h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-login .form-input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-login .btn {
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
        <form class="form-login" method="post">
            <h2 class="mb-4">Login</h2>
            
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-input" required>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-input" required>
            </div>

            <!-- Prikaz poruke o grešci -->
            <% if (showError) { %>
                <div class="error-message">
                    Invalid username or password.
                </div>
            <% } %>
            
            <button class="btn btn-primary" type="submit" name="submit">Sign in</button>
        </form>
    </div>
</body>
</html>