package com.WebApplication.controllers;

import com.WebApplication.models.User;
import com.WebApplication.repository.SQLAdmin;
import com.WebApplication.repository.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class HtmlController {
    SQLAdmin sqlController = new SQLAdmin();
    UserService userService = new UserService();

    @PostMapping("/validateLogin")
    public String validateLogin(WebRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userService.convertRsToUser(sqlController.validateLogin(userService.create(username, password)));
        if (session.getAttribute("username") == null) {
            request.setAttribute("username",
                    userService.convertRsToUser(sqlController.validateLogin
                            (userService.create(username, password))), WebRequest.SCOPE_SESSION);
            return "loginSuccess";
        }
        //return "redirect:/";
        return "loginFailed";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createAccount")
    public String create(WebRequest request) {
        sqlController.createAccount(new User(request.getParameter("username")
                , request.getParameter("password"),
                request.getParameter("email")));
        return "index";
    }

    @GetMapping("/friday")
    public String friday() {
        return "friday";
    }
}