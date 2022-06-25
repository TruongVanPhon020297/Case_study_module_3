package com.codegym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet",urlPatterns = "/homepage")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        String email = "";
        Cookie[] cookies = req.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equals("email")){
                email = c.getValue();
                System.out.println(email);
            }
        }
        if (email.equals("")){
            resp.sendRedirect("/login");
        }else {
            dispatcher.forward(req,resp);
        }
    }
}
