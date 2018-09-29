package com.WebCore.controller;

import com.WebCore.service.DB_interact;
import com.WebCore.service.SecurityService;
import com.WebCore.service.UserService;
import com.WebCore.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.WebCore.model.User;

@Controller
public class UserController {

    @Autowired
    private DB_interact db_interact;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(),
                userForm.getPasswordConfirm());
        return "redirect:/";
    }

}
