<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>You Lose</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index">Guess Game</a>
            <div class="ml-auto">
                <span class="navbar-text">
                    Welcome, ${sessionScope.loggedInUser.username}!
                </span>
                <form class="form-inline d-inline" action="logout" method="get">
                    <button class="btn btn-outline-danger ml-2" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container mt-5 text-center">
        <h1 class="display-4 text-danger">Sorry, you have no more guesses left!</h1>
        <p class="lead mt-4">Better luck next time!</p>
        <a href="guess" class="btn btn-primary btn-lg mt-3">Play Again</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
