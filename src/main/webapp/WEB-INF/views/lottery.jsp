<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.systex.playground.model.Member" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lottery Number Generator</title>
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

    <div class="container mt-5">
        <h1 class="text-center mb-4">Lottery Number Generator</h1>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <%
                    String error = (String) request.getAttribute("error");
                %>
                <% if (error != null && !error.isEmpty()) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= error %>
                    </div>
                <% } %>

                <form action="<%= request.getContextPath() %>/lottery" method="post">
                    <div class="form-group">
                        <label for="excludeNumbers">Exclude Numbers (separate by space):</label>
                        <input type="text" class="form-control" id="excludeNumbers" name="excludeNumbers" placeholder="ex: 3 7 15">
                    </div>
                    <div class="form-group">
                        <label for="loopCount">Loop Count:</label>
                        <input type="text" class="form-control" id="loopCount" name="loopCount" placeholder="Enter a positive integer">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Generate</button>
                </form>
                <div class="text-center mt-3">
                    <a href="index" class="btn btn-secondary">Back to Home</a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
