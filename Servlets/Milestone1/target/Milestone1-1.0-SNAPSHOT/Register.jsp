<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="./CSsStyling/RegisterStyles.css"> 
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
        <form action="RegisterServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname" required><br><br>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required><br><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>
            <input type="submit" value="Register">
        </form>

        <%
            String error = request.getParameter("error");
            if (error != null) {
                if ("missingFields".equals(error)) {
                    out.println("<p class='error-message'>Please fill in all fields.</p>");
                } else if ("usernameOrEmailTaken".equals(error)) {
                    out.println("<p class='error-message'>Username or email is already taken.</p>");
                } else if ("databaseError".equals(error)) {
                    out.println("<p class='error-message'>A database error occurred. Please try again later.</p>");
                }
            }
        %>
    </div>
</body>
</html>


