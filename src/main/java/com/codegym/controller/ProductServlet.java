package com.codegym.controller;

import com.codegym.dao.CategoryService;
import com.codegym.dao.CategoryServiceImpl;
import com.codegym.dao.ProductService;
import com.codegym.dao.ProductServiceImpl;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.utils.UploadFile;
import com.codegym.utils.Validate;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "ProductServlet",urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    CategoryService categoryService;
    ProductService productService;
    ProductServiceImpl service;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryServiceImpl();
        productService = new ProductServiceImpl();
        service = new ProductServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" :
                createUser(req,resp);
                break;
            case "edit" :
                editUser(req,resp);
                break;
            default:
                listUser(req,resp);
                break;
        }
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        String strIdProduct = req.getParameter("id_product");
        String title = req.getParameter("title");
        String strPrice = req.getParameter("price");
        String strQuantity = req.getParameter("quantity");
        String strCategoryId = req.getParameter("category_id");
        List<String> errors = new ArrayList<>();
        if (title.equals("")) {
            errors.add("Không Được Để Trống Tiêu Đề Sản Phẩm");
        }
        if (strPrice.equals("")) {
            errors.add("Không Được Để Trống Giá Sản Phẩm");
        }
        if (strQuantity.equals("")) {
            errors.add("Không Được Để Trống Số Lượng Sản Phẩm");
        }
        if (strCategoryId.equals("")) {
            errors.add("Vui Lòng Chọn Danh Mục Sản Phẩm");
        }

        if (title.length() > 255 || title.length() < 3) {
            errors.add("Tiêu Đề Sản Phẩm Không Hợp Lệ");
        }

        boolean priceIsNumber = Validate.isNumberValid(strPrice);
        if (!priceIsNumber || strPrice.length() > 12 || Integer.parseInt(strPrice) < 0) {
            errors.add("Giá Của Sản Phẩm Không Hợp Lệ");
        }

        boolean quantityIsNumber = Validate.isNumberValid(strQuantity);
        if (!quantityIsNumber || Integer.parseInt(strQuantity) < 0 || strQuantity.length() > 10) {
            errors.add("Số Lượng Sản Phẩm Không Hợp Lệ");
        }

        boolean idCategoryIsNumber = Validate.isNumberValid(strCategoryId);
        if (!idCategoryIsNumber) {
            errors.add("Danh Mục Sản Phẩm Không Hợp Lệ");
        }

        boolean strIdProductIsNumber = Validate.isNumberValid(strIdProduct);
        if (!strIdProductIsNumber) {
            errors.add("Id Sản Phẩm Không Hợp Lệ");
        }


        boolean checkFile = UploadFile.checkFile(req);
        boolean exists = productService.existsById(Integer.parseInt(strIdProduct));
        boolean success = false;
        String message = "";
        if (checkFile == true) {
            if (exists) {
                boolean existsCategory = categoryService.existById(Integer.parseInt(strCategoryId));
                if (existsCategory) {
                    List<Product> productStatus = service.findStatusProductId(Integer.parseInt(strIdProduct));
                    if (productStatus.get(0).getStopSelling() == 1){
                        errors.add("Sản Phẩm Đang Dừng Bán, Vui Lòng Thay Đổi Trạng Thái Của Sản Phẩm Để Sửa Thông Tin");
                    }else {
                        if (errors.size() == 0){
                            Product product = new Product(Integer.parseInt(strIdProduct), title, BigDecimal.valueOf(Integer.parseInt(strPrice)), Integer.parseInt(strQuantity), Integer.parseInt(strCategoryId));
                            Map<String, String> result = productService.updateNo(product);
                            success = Boolean.parseBoolean(result.get("success"));
                            message = result.get("message");
                            if (!success) {
                                errors.add(message);
                            }
                        }
                    }
                    if (success){
                        req.setAttribute("message",message);
                    }
                } else {
                    errors.add("Danh Mục Sản Phẩm Không Tồn Tại");
                }
            }else {
                errors.add("Id Không tồn Tại");
            }
        } else {
                boolean checkNoFile = UploadFile.checkNoFile(req);
                if (!checkNoFile) {
                    errors.add("File Không Hợp Lệ");
                }else {
                    if (exists) {
                        boolean existsCategory = categoryService.existById(Integer.parseInt(strCategoryId));
                        String urlFile = UploadFile.uploadImagesServer(req);
                        if (existsCategory) {
                            List<Product> productStatus = service.findStatusProductId(Integer.parseInt(strIdProduct));
                            if (productStatus.get(0).getStopSelling() == 1){
                                errors.add("Sản Phẩm Đang Dừng Bán, Vui Lòng Thay Đổi Trạng Thái Của Sản Phẩm Để Sửa Thông Tin");
                            }else {
                                if (errors.size() == 0){
                                    Product product = new Product(Integer.parseInt(strIdProduct), title, urlFile,BigDecimal.valueOf(Integer.parseInt(strPrice)), Integer.parseInt(strQuantity), Integer.parseInt(strCategoryId));
                                    Map<String, String> result = productService.update(product);
                                    success = Boolean.parseBoolean(result.get("success"));
                                    message = result.get("message");
                                    if (!success) {
                                        errors.add(message);
                                    }
                                }
                                if (success){
                                    req.setAttribute("message",message);
                                }
                            }
                        } else {
                            errors.add("Danh Mục Sản Phẩm Không Tồn Tại");
                        }
                    }else {
                        errors.add("Id Không tồn Tại");
                    }
                }
            }

            if (errors.size() > 0) {
                req.setAttribute("errors", errors);
                List<Product> productFind = productService.findProductId(Integer.parseInt(strIdProduct));
                req.setAttribute("productFind", productFind);
            }
            List<Category> categoryList = categoryService.findAll();
            req.setAttribute("categoryList", categoryList);
            dispatcher.forward(req, resp);
        }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        String title = req.getParameter("title");
        String strPrice = req.getParameter("price");
        String strQuantity = req.getParameter("quantity");
        String strCategory_id = req.getParameter("category_id");
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList",categoryList);
        List<String> errors = new ArrayList<>();
        if(title.equals("")) {
            errors.add("Không Được Để Trống Tiêu Đề Sản Phẩm");
        }
        if(strPrice.equals("")) {
            errors.add("Không Được Để Trống Giá Sản Phẩm");
        }
        if (strQuantity.equals("")) {
            errors.add("Không Được Để Trống Số Lượng Sản Phẩm");
        }
        if(strCategory_id.equals("")) {
            errors.add("Vui Lòng Chọn Danh Mục Sản Phẩm");
        }

        if(title.length() > 255 || title.length() < 3) {
            errors.add("Tiêu Đề Sản Phẩm Không Hợp Lệ");
        }

        boolean priceIsNumber = Validate.isNumberValid(strPrice);
        if (!priceIsNumber || strPrice.length() > 12 || Integer.parseInt(strPrice) < 0) {
            errors.add("Giá Của Sản Phẩm Không Hợp Lệ");
        }

        boolean quantityIsNumber = Validate.isNumberValid(strQuantity);
        if (!quantityIsNumber || Integer.parseInt(strQuantity) < 0 || Integer.parseInt(strQuantity) > 2147483647) {
            errors.add("Số Lượng Sản Phẩm Không Hợp Lệ");
        }

        boolean checkFile = UploadFile.checkFile(req);
        if (checkFile){
            errors.add("Vui Lòng Chọn File");
        }else {
            boolean checkNoFile = UploadFile.checkNoFile(req);
            if (!checkNoFile){
                errors.add("File Không Hợp Lệ");
            }
        }

        boolean idCategoryIsNumber = Validate.isNumberValid(strCategory_id);
        if (!idCategoryIsNumber) {
            errors.add("Danh Mục Sản Phẩm Không Hợp Lệ");
        }

        if (errors.size() == 0) {
            boolean exist = categoryService.existById(Integer.parseInt(strCategory_id));
            boolean success = false;
            if (exist){
                String urlImage = UploadFile.uploadImagesServer(req);
                Product product = new Product(title,urlImage, BigDecimal.valueOf(Long.parseLong(strPrice)),Integer.parseInt(strQuantity),Integer.parseInt(strCategory_id));
                Map<String, String> result = productService.doCreate(product);
                success = Boolean.parseBoolean(result.get("success"));
                String message = result.get("message");
                if (!success){
                    errors.add(message);
                }else {
                    req.setAttribute("success", true);
                }
            }else {
                errors.add("Danh Mục Sản Phẩm Không tồn tại");
            }
        }
        if (errors.size() > 0){
            req.setAttribute("errors",errors);
        }
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
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
            switch (action) {
                case "create" :
                    ShowCreateUser(req,resp);
                    break;
                case "edit" :
                    ShowEditUser(req,resp);
                    break;
                case "block":
                    blockUser(req, resp);
                    break;
                default:
                    listUser(req,resp);
                    break;
            }
        }
    }

    private void blockUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = -1;
        if(req.getParameter("id")!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }
        List<Product> productList = service.findStatusProductId(id);
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        if (productList.isEmpty()){
            printWriter.println("fail");
        }else {
            int status = productList.get(0).getStopSelling();
            if (status == 1){
                status = 0;
            }else {
                status = 1;
            }
            boolean update = service.updateStatus(id,status);
            if (update){
                List<Product> productList1 = service.findStatusProductId(id);
                Gson gson = new Gson();
                String jsonInString = gson.toJson(productList1.get(0));
                printWriter.println(jsonInString);
            }
        }

        printWriter.close();

    }

    private void ShowEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        String strProductId = req.getParameter("id");
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList",categoryList);
        boolean productIdIsNumber = Validate.isNumberValid(strProductId);
        List<String> errors = new ArrayList<>();
        if (!productIdIsNumber){
            errors.add("Id Sản Phẩm Không Hợp Lệ");
        }else {
            boolean exists = productService.existsById(Integer.parseInt(strProductId));
            if (!exists) {
                errors.add("Sản Phẩm Không Tồn Tại");
            }else {
                List<Product> productFind = productService.findProductId(Integer.parseInt(strProductId));
                if (productFind.size() == 0) {
                    errors.add("Sản Phẩm Đang Dừng Bán, Vui Lòng Thay Đổi Trạng Thái Của Sản Phẩm Để Sửa Thông Tin");
                }else {
                    req.setAttribute("productFind",productFind);
                }
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors",errors);
        }
        dispatcher.forward(req,resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        int page = 1;
        int recordsPerPage = 5;
        String strPage = req.getParameter("page");
        List<String> error = new ArrayList<>();
        String keySearch = req.getParameter("key_search");
        if(keySearch != null) {
            List<Product> productList = service.searchByKey(keySearch);
            req.setAttribute("productList",productList);
            req.setAttribute("message","Kết Quả Tìm Kiếm");
            dispatcher.forward(req,resp);
        }else {
            if( strPage != null){
                boolean pageIsNumber = Validate.isNumberValid(req.getParameter("page"));
                if (!pageIsNumber) {
                    error.add("Page Không Hợp Lệ");
                }else {
                    page = Integer.parseInt(req.getParameter("page"));
                }
            }
            if (error.size() == 0) {
                List<Category> categoryList = categoryService.findAll();
                List<Product> productList  = service.findAll((page-1)*recordsPerPage,recordsPerPage);
                int noOfRecords = service.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                System.out.println("noOfRecords " + noOfRecords);
                System.out.println("noOfPages " + noOfPages);
                req.setAttribute("categoryList",categoryList);
                req.setAttribute("productList",productList);
                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
                if (productList.isEmpty()){
                    error.add("Không Có Dữ Liệu Phù Hợp Với Page");
                }
            }
            if (error.size() > 0){
                req.setAttribute("error",error);
            }
            dispatcher.forward(req,resp);
        }
    }

    private void ShowCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList",categoryList);
        dispatcher.forward(req,resp);
    }
}
