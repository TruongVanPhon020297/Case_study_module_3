package com.codegym.controller.api;


import com.codegym.dao.CategoryService;
import com.codegym.dao.CategoryServiceImpl;
import com.codegym.dao.ProductService;
import com.codegym.dao.ProductServiceImpl;
import com.codegym.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductApiServlet", urlPatterns = "/api/products")
public class ProductApiServlet extends HttpServlet {

    CategoryService categoryService;
    ProductService productService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "get-all-products":
                getAllProducts(req, resp);
                break;
//            case "edit":
//                showEditProduct(req, resp);
//                break;
//            case "block":
//                blockUser(req, resp);
//                break;
//            default:
//                listProduct(req, resp);
//                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void getAllProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = null;
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        long category = Long.parseLong(req.getParameter("category"));

        List<Product> products = productService.getAllProducts(category);

        json = new Gson().toJson(products);

        resp.getWriter().write(json);

    }
}
