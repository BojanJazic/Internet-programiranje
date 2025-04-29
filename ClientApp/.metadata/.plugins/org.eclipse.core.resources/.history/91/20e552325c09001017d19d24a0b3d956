<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="ISO-8859-1">
    <title>Login and Logout Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="styles/login.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <%-- Check if user is logged in --%>
        <%
            String username = (String) session.getAttribute("username");
            if (username == null) {
        %>
        <!-- Login Form -->
        <form class="form-login" method="post" action="Controller">
            <input type="hidden" name="action" value="login">
            <h2 class="mb-3">Login</h2>
            <% if (session.getAttribute("error") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= session.getAttribute("error") %>
                </div>
                <% session.removeAttribute("error"); // Ukloni poruku o grešci nakon prikaza %>
            <% } %>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button class="btn btn-primary" type="submit">Sign in</button>
            <br /> <a href="Controller?action=register">Create new account &gt;&gt;&gt;</a>
        </form>
        <% } %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>