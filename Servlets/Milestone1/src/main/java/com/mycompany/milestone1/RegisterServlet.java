package com.mycompany.milestone1;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DataHandler dataHandler = new DataHandler();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           // Retrieve parameters from form
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Note: Password should be hashed in production
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        //Is any field empty
        if (isAnyFieldEmpty(username, password, name, surname, phone, email)) {
            response.sendRedirect("Register.jsp?error=missingFields");
            return;
        }

        try {
            // Check if username or email already exists
            if (dataHandler.isUsernameOrEmailTaken(username, email)) {
                response.sendRedirect("Register.jsp?error=usernameOrEmailTaken");
                return;
            }

            // Register the new user
            if (dataHandler.registerUser(username, password, name, surname, phone, email)) {
                response.sendRedirect("Login.jsp");
            } else {
                response.sendRedirect("Register.jsp?error=registrationFailed");
            }
        } catch (SQLException e) {
            // Log and handle SQL exceptions
            e.printStackTrace();
            response.sendRedirect("Register.jsp?error=databaseError");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
