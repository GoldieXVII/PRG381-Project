<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="./CSsStyling/LoginStyles.css"> 
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input placeholder="Enter UserName" type="text" id="username" name="username" required><br><br>
            <label for="password">Password:</label>
            <input placeholder="Enter Password" type="password" id="password" name="password" required><br><br>
            <input type="submit" value="Login">
        </form>

        <%
            String error = request.getParameter("error");
            if (error != null) {
                if ("missingFields".equals(error)) {
                    out.println("<p class='error-message'>Please fill in all fields.</p>");
                } else if ("invalidCredentials".equals(error)) {
                    out.println("<p class='error-message'>Invalid username or password.</p>");
                } else if ("databaseError".equals(error)) {
                    out.println("<p class='error-message'>A database error occurred. Please try again later.</p>");
                }
            }
        %>
    </div>
</body>
</html>


