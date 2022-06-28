package com.codegym.controller;

import com.codegym.dao.*;
import com.codegym.model.Cart;
import com.codegym.model.Category;
import com.codegym.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    CategoryService categoryService;
    ProductService productService;
    CartService cartService;
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryServiceImpl();
        productService = new ProductServiceImpl();
        cartService = new CartServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                ShowCreateOrder(req, resp);
                break;
            default:
                listOrder(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "pay":
                payOrder(req, resp);
                break;
        }
    }

    private void listOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("order/list.jsp");

        List<Cart> carts = cartService.findAll();

        if (carts.size() > 0) {
            req.setAttribute("carts", carts);
        }
        else {
            req.setAttribute("carts", null);
        }

        dispatcher.forward(req, resp);
    }

    private void ShowCreateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("order/create.jsp");
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList", categoryList);
        dispatcher.forward(req, resp);
    }

    private void payOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("order/list.jsp");

        String userId = "0";
        Cookie[] cookies = req.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equals("userId")){
                userId = c.getValue();
            }
        }

        Order order = new Order(Integer.parseInt(userId));
//        order.setUserId(Integer.parseInt(userId));

        Map<String, String> result = orderService.payOrder(order);

        Boolean success = Boolean.parseBoolean(result.get("success"));
        String message = result.get("message");

        req.setAttribute("success", success);
        req.setAttribute("message", message);

        req.setAttribute("carts", null);

        dispatcher.forward(req, resp);

    }
}
