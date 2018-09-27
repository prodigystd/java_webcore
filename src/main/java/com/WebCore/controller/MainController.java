package com.WebCore.controller;

import com.WebCore.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/my_articles")
    public String my_articles() {
        return "my_articles";
    }

    @RequestMapping("/add_article")
    public String add_article(Model model) {
        model.addAttribute("userArticle",new Article());
        return "add_article";
    }

}
