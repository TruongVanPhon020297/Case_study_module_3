package com.codegym.controller;

import com.codegym.dao.LoginService;
import com.codegym.dao.LoginServiceImpl;
import com.codegym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = new LoginServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        String email = "";
        Cookie[] cookies = req.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equals("email")){
                email = c.getValue();
                System.out.println(email);
            }
        }
        if (email.equals("")){
            dispatcher.forward(req,resp);
        }else {
            resp.sendRedirect("/homepage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<String> errors = new ArrayList<>();
        if (email.equals("")) {
            errors.add("Không Được Để Trống Email");
        }
        if (password.equals("")) {
            errors.add("Không Được Để Trống Mật Khẩu");
        }

        boolean existEmail = loginService.existsEmail(email);
        if (existEmail){
            List<User> userList = loginService.findUserByEmail(email);
            if (userList.get(0).getAdmin() == 0) {
                errors.add("Tài Khoản Không Phải Admin Để Đăng Nhập Vào Hệ Thống Này");
            }else {
                if (userList.get(0).getStatus() == 0) {
                    errors.add("Tài Khoản Đang Bị Khóa Không Thể Đăng Nhập");
                }else {
                    if (!userList.get(0).getPasswordUser().equals(password)){
                        errors.add("Sai Mật Khẩu Vui Lòng Kiểm Tra Lại Mật Khẩu");
                    }
                }
            }
        }else {
            errors.add("Email Không Tồn Tại");
        }

        if (errors.size() > 0) {
            req.setAttribute("errors",errors);
            dispatcher.forward(req,resp);
        }

        if (errors.size() == 0) {
            List<User> userList = loginService.findUserByEmail(email);
            Cookie email1 = new Cookie("email",email);
            email1.setMaxAge(60*5);
            resp.addCookie(email1);
            resp.sendRedirect("/homepage");
        }
    }
}
