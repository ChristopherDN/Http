package com.WebApplication.controllers;

import com.WebApplication.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class HtmlController {
    SQLcontroller sqLcontroller = new SQLcontroller();

    @PostMapping("/validateLogin")
    public String validateLogin(WebRequest request, HttpSession session) {
        User user = new User(request.getParameter("username"),request.getParameter("password"));
        return sqLcontroller.validateLogin(user,request,session);
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createAccount")
    public String createAccount(WebRequest request){
        User user = new User(request.getParameter("username")
                ,request.getParameter("password"),
                request.getParameter("email"));
        sqLcontroller.users.add(user);
        sqLcontroller.scriptCommand("insert into Users.accounts(username, password, email)values" +
                "("+"\"" + user.getUsername() +"\",\"" + user.getPassword() + "\",\"" + user.getEmail() +"\")");
        return "index";
    }

    @GetMapping("/friday")
    public String friday() {
        return "friday";
    }
}