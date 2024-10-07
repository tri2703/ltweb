package ute.tri.controllers;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ute.tri.services.IUserService;
import ute.tri.services.impl.UserService;
import ute.tri.utils.Constant;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			resp.sendRedirect(req.getContextPath() + "/register");
			return;
		}
//Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/register");
					return;
				}
			}
		}
		req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setCharacterEncoding("UTF-8");
	    req.setCharacterEncoding("UTF-8");
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String email = req.getParameter("email");
	    String fullname = req.getParameter("fullname");
	    String phone = req.getParameter("phone");
	    IUserService service = new UserService();
	    String alertMsg = "";

	    // Kiểm tra tồn tại email
	    if (service.checkExistEmail(email)) {
	        alertMsg = "Email đã tồn tại!";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	        return;
	    }

	    // Kiểm tra tồn tại username
	    if (service.checkExistUsername(username)) {
	        alertMsg = "Tài khoản đã tồn tại!";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	        return;
	    }

	    // Đăng ký người dùng
	    boolean isSuccess = service.register(username, password, email, fullname, phone);
	    if (isSuccess) {
	        req.setAttribute("alert", alertMsg);
	        resp.sendRedirect(req.getContextPath() + "/login");
	    } else {
	        alertMsg = "Có lỗi xảy ra trong hệ thống! Vui lòng thử lại sau.";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	        // Log lỗi chi tiết để kiểm tra
	        System.err.println("Registration failed for user: " + username);
	    }
	}

}