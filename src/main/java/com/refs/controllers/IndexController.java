package com.refs.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {
    public String hello;


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index pagellls gdf hhhh");
        //System.out.print("Hello World");


        //model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
