package ute.tri.controllers;
import ute.tri.services.IUserService;
import ute.tri.services.impl.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reset-password")
public class PasswordResetController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String newPassword = req.getParameter("password");

        boolean success = userService.updatePassword(username, newPassword);

        if (success) {
            resp.sendRedirect("views/login.jsp");
        } else {
            req.setAttribute("error", "Failed to reset password");
            req.getRequestDispatcher("views/resetPasswordForm.jsp").forward(req, resp);
        }
    }
}