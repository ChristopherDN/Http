package com.WebApplication.controllers;

import com.WebApplication.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class HtmlController {
    SQLcontroller sqLcontroller = new SQLcontroller();
    ArrayList <User> users = sqLcontroller.getResults(sqLcontroller.scriptRecieve("select * from users.accounts"));


    @PostMapping("/validateLogin")
    public String validateLogin(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return "loginSuccess";
            }
        }
        return "loginFailed";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "email") String email){
        users.add(new User(username,password,email));
        sqLcontroller.scriptCommand("insert into Users.accounts(username, password, email)values" +
                "("+"\"" + username +"\",\"" + password + "\",\"" + email +"\")");
        return "index";
    }
    @GetMapping("/friday")
    public String friday() {
        return "friday";
    }

   /* @PostMapping("/login")
    public String submitForm(@ModelAttribute("user") User user){
        if(user.getUsername().equals("Chris")&&user.getPassword().equals("1234")){
            return "loginSuccess";
        } else{
            return "loginFailed";
        }*/

}




