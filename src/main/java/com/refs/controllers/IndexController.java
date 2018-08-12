package com.refs.controllers;

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


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index pagellls gdf hhhh");
        //System.out.print("Hello World");


    Set<String> advertisement = new HashSet<>();
        model.addAttribute("advertisements", advertisement);

        return "index";
    }
}
