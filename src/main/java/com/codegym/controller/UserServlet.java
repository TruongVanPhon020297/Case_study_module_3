package com.codegym.controller;

import com.codegym.dao.UserService;
import com.codegym.dao.UserServiceImpl;
import com.codegym.model.User;
import com.codegym.utils.ValidateUtils;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    UserService userService;
    UserServiceImpl service;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        service = new UserServiceImpl();
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
                case "create":
                    createUser(req, resp);
                    break;
                case "edit":
                    editUser(req, resp);
                    break;
                case "block":
                    blockUser(req, resp);
                    break;
                default:
                    listUser(req, resp);
                    break;
            }
        }
    }

    private void blockUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = -1;
        if(req.getParameter("id")!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }
        List<User> userList = service.findStatusUserId(id);
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        if (userList.isEmpty()){
            printWriter.println("fail");
        }else {
            int status = userList.get(0).getStatus();
            if (status == 1){
                status = 0;
            }else {
                status = 1;
            }
            boolean update = service.updateStatus(id,status);
            if (update){
                List<User> userList1 = service.findStatusUserId(id);
                Gson gson = new Gson();
                String jsonInString = gson.toJson(userList1.get(0));
                printWriter.println(jsonInString);
            }
        }

        printWriter.close();
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
                doCreateUser(req,resp);
                break;
            case "edit" :
                doEditUser(req,resp);
                break;
            default:
                listUser(req,resp);
                break;
        }
    }

    private void doEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/edit.jsp");
        String idUser = req.getParameter("id_user");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        List<String> errors = new ArrayList<>();

        if (idUser == null) {
            errors.add("Id Kh??ng t???n t???i");
        }
        if (fullName.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng T??n");
        }
        if (email.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng Email");
        }
        if (mobile.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng Email");
        }
        if (address.equals("")) {
            errors.add("Kh??ng ???????c ????? Tr???ng ?????a Ch??? Kh??ch H??ng");
        }

        boolean checkEmail = ValidateUtils.isEmail(email);
        if (!checkEmail) {
            errors.add("Email Kh??ng H???p L???");
        }

        boolean checkMobile = ValidateUtils.isPhone(mobile);
        if (!checkMobile) {
            errors.add("S??? ??i???n Tho???i Kh??ng H???p L???");
        }
        boolean success = false;
        String message = "";
        boolean idIsNumber = ValidateUtils.isNumberValid(idUser);
        if (!idIsNumber) {
            errors.add("Id Kh??ng H???p L???");
        }else {
            boolean existsById = service.existsById(Integer.parseInt(idUser));
            if (!existsById) {
                errors.add("Id Kh??ng T???n T???i Tr??n H??? Th???ng");
            }else {
                List<User> userListFindStatus = service.findStatusUserId(Integer.parseInt(idUser));
                if (userListFindStatus.get(0).getStatus() == 0) {
                    errors.add("Ng?????i D??ng ??ang B??? Kh??a, Vui L??ng Thay ?????i Tr???ng Th??i  ????? S???a Th??ng Tin");
                }else {
                    List<User> userList = service.findUserId(Integer.parseInt(idUser));
                    boolean existsEmail = service.existsByEmail(email);
                    if (!userList.get(0).getEmail().equals(email) && existsEmail == true) {
                        errors.add("Email ???? T???n T???i Vui L??ng Nh???p Email Kh??c");
                    }

                    boolean existsMobile = service.existsByMobile(mobile);
                    if (!userList.get(0).getMobile().equals(mobile) && existsMobile == true) {
                        errors.add("S??? ??i???n Tho???i ???? T???n T???i Vui L??ng Nh???p S??? ??i???n Tho???i Kh??c");
                    }
                    if (errors.size() == 0) {
                        User user = new User(Integer.parseInt(idUser),fullName,mobile,email,address);
                        Map<String, String> result = service.update(user);
                        success = Boolean.parseBoolean(result.get("success"));
                        message = result.get("message");
                        if (!success) {
                            errors.add(message);
                        }
                    }
                }
            }
        }


        if (errors.size() == 0) {
            if (success) {
                req.setAttribute("success", true);
                req.setAttribute("message",message);
                List<User> userFind = service.findUserId((Integer.parseInt(idUser)));
                req.setAttribute("userFind",userFind);
            }
            else {
                errors.add("S???a Th??ng Tin Ng?????i D??ng Th???t B???i");
            }
        }

        if (errors.size()>0){
            req.setAttribute("errors",errors);
            List<User> userFind = service.findUserId((Integer.parseInt(idUser)));
            req.setAttribute("userFind",userFind);
        }

        dispatcher.forward(req,resp);




    }

    private void doCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullname");
        String strMobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String strRole = req.getParameter("role");
        List<String> errors = new ArrayList<>();
        if (email.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng Email");
        }
        if (password.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng M???t Kh???u");
        }
        if (fullName.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng T??n Ng?????i D??ng");
        }
        if (strMobile.equals("")) {
            errors.add("Kh??ng ???????c ????? Tr???ng S??? ??i???n Tho???i");
        }
        if (address.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng ?????a Ch???");
        }
        if (strRole.equals("")){
            errors.add("Kh??ng ???????c ????? Tr???ng Role");
        }

        boolean passwordIsValidate = ValidateUtils.isPasswordValid(password);
        if (!passwordIsValidate) {
            errors.add("M???t Kh???u Kh??ng ????ng ?????nh D???ng");
        }

        if (fullName.length() < 3 || fullName.length() > 255) {
            errors.add("T??n S???n Ph???m Kh??ng H???p L???");
        }

        boolean checkMobile = ValidateUtils.isPhone(strMobile);
        if (!checkMobile) {
            errors.add("S??? ??i???n Tho???i Kh??ng H???p L???");
        }

        boolean roleIsNumber = ValidateUtils.isNumberValid(strRole);
        if (!roleIsNumber || Integer.parseInt(strRole) < 0 || Integer.parseInt(strRole) > 1){
            errors.add("Lo???i T??i Kho???n Kh??ng H???p L???");
        }

        boolean checkIsEmail = ValidateUtils.isEmail(email);
        if (!checkIsEmail){
            errors.add("Email Kh??ng H???p L???");
        }

        boolean checkEmail = userService.existsByEmail(email);
        if (checkEmail) {
            errors.add("Email ???? T???n T???i Vui L??ng Nh???p Email Kh??c");
        }

        boolean checkMobileExists = userService.existsByMobile(strMobile);
        if (checkMobileExists) {
            errors.add("S??? ??i???n Tho???i ???? T???n T???i Vui L??ng Nh???p S?? ??i???n Tho???i Kh??c");
        }

        boolean success = false;
        if (errors.size() == 0) {
            User user = new User(fullName,strMobile,email,password,Integer.parseInt(strRole),address);
            Map<String, String> result = userService.doCreate(user);
            success = Boolean.parseBoolean(result.get("success"));
            String message = result.get("message");
            if (!success){
                errors.add(message);
            }else {
                req.setAttribute("success", true);
            }
        }
        if (errors.size() > 0){
            req.setAttribute("errors",errors);
        }
        dispatcher.forward(req,resp);
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/edit.jsp");
        String strUserId = req.getParameter("id");
        boolean userIdIsNumber = ValidateUtils.isNumberValid(strUserId);
        List<String> errors = new ArrayList<>();
        if (!userIdIsNumber){
            errors.add("Id Ng?????i D??ng Kh??ng H???p L???");
        }else {
            boolean exists = service.existsById(Integer.parseInt(strUserId));
            if (!exists) {
                errors.add("Ng?????i D??ng Kh??ng T???n T???i");
            }else {
                List<User> userFind = service.findUserId((Integer.parseInt(strUserId)));
                if (userFind.size() == 0) {
                    errors.add("Ng?????i D??ng ??ang B??? Kh??a, Vui L??ng Thay ?????i Tr???ng Th??i  ????? S???a Th??ng Tin");
                }else {
                    req.setAttribute("userFind",userFind);
                }
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors",errors);
        }
        dispatcher.forward(req,resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        int page = 1;
        int recordsPerPage = 5;
        String keySearch = req.getParameter("key_search");
        List<String> error = new ArrayList<>();
        String strPage = req.getParameter("page");
        if(keySearch != null) {
            List<User> userList = service.searchByKey(keySearch);
            req.setAttribute("userList",userList);
            req.setAttribute("message","K???t Qu??? T??m Ki???m");
        }else {
            if( strPage != null){
                boolean pageIsNumber = ValidateUtils.isNumberValid(req.getParameter("page"));
                if (!pageIsNumber) {
                    error.add("Page Kh??ng H???p L???");
                }else {
                    page = Integer.parseInt(req.getParameter("page"));
                }
            }
            if (error.size() == 0) {
                List<User> userList  = service.findAll((page-1)*recordsPerPage,recordsPerPage);
                int noOfRecords = service.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
                req.setAttribute("userList",userList);
                if (userList.isEmpty()){
                    error.add("Kh??ng C?? D??? Li???u Ph?? H???p V???i Page");
                }
            }
            if (error.size() > 0){
                req.setAttribute("error",error);
            }
        }
        dispatcher.forward(req, resp);
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(req,resp);
    }
}
