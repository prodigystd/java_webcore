package com.WebCore.controller;

import com.WebCore.model.Article;
import com.WebCore.service.DB_interact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @Autowired
    DB_interact db_interact;

    @RequestMapping("/my_articles")
    public String my_articles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName(); //get logged in username
        model.addAttribute("UserArticles",
                db_interact.getUserArticles(db_interact.get_user(user_name))
        );
        return "my_articles";
    }

    @RequestMapping(value = "/add_article",method = RequestMethod.POST)
    public String add_article(@ModelAttribute("userArticle") Article userArticle,
                              BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName();
        userArticle.setUser_id(db_interact.get_user(user_name).getId());
       /* userArticle.setArticleContent(
                userArticle.
                        getArticleContent().
                        replace("\n","<BR>"));*/
        db_interact.save_article(userArticle);
        return "redirect:/my_articles";
    }

    @GetMapping("/add_article")
    public String add_article(Model model) {
        model.addAttribute("userArticle",new Article());
        model.addAttribute("Action","Add article");
        return "article";
    }

    @RequestMapping("/article/{article_id}/edit")
    public String add_article(@PathVariable("article_id") long article_id, Model model) {
        model.addAttribute("userArticle",db_interact.getArticle(article_id));
        model.addAttribute("Action","Edit article");
        return "article";
    }

}
