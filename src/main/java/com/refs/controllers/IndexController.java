package com.refs.controllers;

import com.refs.models.Category;
import com.refs.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Controller
public class IndexController {
    public String hello;
    public CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index pagellls gdf hhhh");
        //System.out.print("Hello World");


    Set<String> advertisement = new HashSet<>();
        model.addAttribute("advertisements2", advertisement);
        model.addAttribute("categories", categoryService.getCategories());

        return "index";
    }

    @RequestMapping({"/login", "/login"})
    public String getLoginForm(Model model) {
        return "login";
    }

}
