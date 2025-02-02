package com.PriortizeMe.servlet;

import com.PriortizeMe.dao.UserDao;
import com.PriortizeMe.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hashing the password before storing it
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        UserDao userDao = new UserDao();
        if (userDao.registerUser(user)) {
            response.sendRedirect("login.html"); // Redirecting to login page after registration
        } else {
            response.sendRedirect("error.jsp?msg=Error registering user!"); // Redirect to error page if registration fails
        }
    }
}
