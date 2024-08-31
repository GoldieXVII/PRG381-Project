<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="./CSsStyling/WelcomeStyles.css">
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome, ${sessionScope.username}!</h1>
        <a href="Logout.jsp">Logout</a>
    </div>
</body>
</html>

