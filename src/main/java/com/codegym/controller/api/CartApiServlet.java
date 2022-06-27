package com.codegym.controller.api;

import com.codegym.dao.CartService;
import com.codegym.dao.CartServiceImpl;
import com.codegym.model.Cart;
import com.codegym.utils.ValidateUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "CartApiServlet", urlPatterns = "/api/carts")
public class CartApiServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                addToCart(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = null;
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String productId = req.getParameter("product_id");
        String quantity = req.getParameter("quantity");

        Map<String, String> result = new HashMap<>();

        if (!ValidateUtils.isNumberValid(productId)) {
            result.put("success", "false");
            result.put("message", "ID sản phẩm không hợp lệ");
        }
        else if (!ValidateUtils.isNumberValid(quantity)) {
            result.put("success", "false");
            result.put("message", "Số lượng không hợp lệ");
        }
        else {
            Cart cart = new Cart(Integer.parseInt(productId), Integer.parseInt(quantity));
            result = cartService.addToCart(cart);
        }

        json = new Gson().toJson(result);
        resp.getWriter().write(json);
    }
}
