<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (request.getParameter("logout") != null) {
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if (userBean != null) {
            userBean.setLoggedIn(false);
        }
        session.invalidate();
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>MainPage</title>
    <style>
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
        }

        .btn.btn-primary {
            width: 30vw;
            height: 5vh;
            margin-bottom: 10px;
        }
    </style>
    <script>
        function redirectTo(page) {
            window.location.href = page;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Marketing</h2>

        <!-- Dugme za Promotions -->
        <button class="btn btn-primary" type="button" onclick="redirectTo('promotions.jsp')">Promotions</button>

        <!-- Dugme za Posts -->
        <button class="btn btn-primary" type="button" onclick="redirectTo('posts.jsp')">Posts</button>

        <!-- Dugme za Add Promotion -->
        <button class="btn btn-primary" type="button" onclick="redirectTo('addPromotion.jsp')">Add promotion</button>

        <!-- Dugme za Add Post -->
        <button class="btn btn-primary" type="button" onclick="redirectTo('addPost.jsp')">Add post</button>

        <!-- Dugme za Logout -->
        <form action="mainPage.jsp" method="get">
            <button class="btn btn-primary" type="submit" name="logout">Logout</button>
        </form>
    </div>
</body>
</html>