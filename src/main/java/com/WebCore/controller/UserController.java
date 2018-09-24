package com.WebCore.controller;

import com.WebCore.DB_interact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.WebCore.model.User;

@Controller
public class UserController {

    private DB_interact db_interact = new DB_interact();

    @RequestMapping("/main")
    public String index() {
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET )
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("Users",db_interact.getUsers());
        return "hello";
    }
}
