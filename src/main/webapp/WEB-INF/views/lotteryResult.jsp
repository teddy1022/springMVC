<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.systex.playground.model.Member" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lottery Result</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index">Lottery Number Generator</a>
            <div class="ml-auto">
                <span class="navbar-text">
                    Welcome,
                    <% 
                        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
                        String username = (loggedInUser != null) ? loggedInUser.getUsername() : "Guest";
                    %>
                    <%= username %>!
                </span>
                <form class="form-inline d-inline" action="logout" method="get">
                    <button class="btn btn-outline-danger ml-2" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container mt-5 text-center">
        <h1 class="mb-4">Generated Lottery Numbers</h1>
        <div class="card">
            <div class="card-body">
                <pre  class="text-monospace" style="font-size: 1.5rem;"><%= request.getAttribute("result") %></pre>
            </div>
        </div>
        <a href="lottery" class="btn btn-primary btn-lg mt-4">Generate Again</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
