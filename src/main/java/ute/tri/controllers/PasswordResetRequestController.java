package ute.tri.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.tri.models.UserModel;
import ute.tri.services.IUserService;
import ute.tri.services.impl.UserService;

@WebServlet("/reset-password-request")
public class PasswordResetRequestController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/resetPasswordRequest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        
        UserModel user = userService.FindByUserName(username); // Corrected line

        if (user != null) {
            req.setAttribute("username", username);
            req.getRequestDispatcher("views/resetPasswordForm.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Username not found");
            req.getRequestDispatcher("views/resetPasswordRequest.jsp").forward(req, resp);
        }
    }
}
