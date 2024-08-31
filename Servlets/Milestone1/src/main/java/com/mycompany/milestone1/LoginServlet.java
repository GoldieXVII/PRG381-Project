package com.mycompany.milestone1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DataHandler dataHandler = new DataHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Gets parameters from form
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Note: Password should be hashed in production

        // Validation if the fields have a value in them
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("Login.jsp?error=missingFields");
            return;
        }

        // Validate user
        try {
            if (dataHandler.validateUser(username, password)) {
                // Create a new session and set the username attribute
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Redirect to welcome page after successful login
                response.sendRedirect("Welcome.jsp");
            } else {
                // Invalid credentials
                response.sendRedirect("Login.jsp?error=invalidCredentials");
            }

        } catch (SQLException e) {
            // Log the exception with detailed information
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace(); 

            // Redirect to login page with an error message
            response.sendRedirect("Login.jsp?error=databaseError");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
