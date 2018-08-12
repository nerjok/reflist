package com.refs.controllers;


import com.refs.commands.AdvertisementCommand;
import com.refs.commands.CategoryCommand;
import com.refs.commands.CommentCommand;
import com.refs.exceptions.NotFoundException;
import com.refs.models.Advertisement;
import com.refs.models.Category;
import com.refs.services.AdvertisementService;
import com.refs.services.CategoryService;
import com.refs.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final CategoryService category;
    private final CommentService comment;
    private static final String ADVERTISEMENT_ADVERTISEMETFORM_URL = "advertisements/advertisementform";

    public AdvertisementController(AdvertisementService advertisementService, CategoryService category, CommentService comment) {
        this.advertisementService = advertisementService;
        this.category = category;
        this.comment = comment;
    }

    @RequestMapping({"advertisement/", "advertisement"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index pagellls gdf hhhh");
        //System.out.print("Hello World");


        model.addAttribute("advertisements", advertisementService.getAdvertisements());

        return "advertisements/index";
    }

    @GetMapping("/advertisement/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("advertisement", advertisementService.findById(new Long(id)));
        model.addAttribute("comment", new CommentCommand());

        return "advertisements/show";
    }

    @GetMapping("advertisement/new")
    public String newRecipe(Model model){
        model.addAttribute("advertisement", new AdvertisementCommand());
        model.addAttribute("categories", category.getCategories());
        return "advertisements/advertisementform";
    }

    @GetMapping("advertisement/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        AdvertisementCommand advertisement = advertisementService.findCommandById(Long.valueOf(id));
        //log.debug(advertisement.getCategories().toString());
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("categories", category.getCategories());
        if(advertisement.categories != null)
        System.out.println(advertisement.categories.length);
        return "advertisements/advertisementform";
    }

    @PostMapping({"advertisement", "advertisement/"})
    public String saveOrUpdate(@Valid @ModelAttribute("advertisement") AdvertisementCommand command, BindingResult bindingResult){
        //return "redirect:/advertisement";

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            // return "advertisements/advertisementform";
            return ADVERTISEMENT_ADVERTISEMETFORM_URL;
        }

        AdvertisementCommand savedCommand = advertisementService.saveAdvertisementCommand(command);

        return "redirect:/advertisement/" + savedCommand.getId() + "/show";

    }

    @GetMapping("advertisement/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        advertisementService.deleteById(Long.valueOf(id));
        return "redirect:/advertisement";
    }


    @PostMapping("advertisement/{id}/comment")
    public String saveComment(@PathVariable String id , Model model, @ModelAttribute CommentCommand command) {

        CommentCommand savedComment = comment.saveCommentCommand(command);

        model.addAttribute("advertisement", advertisementService.findById(new Long(id)));
        model.addAttribute("comment", new CommentCommand());



        return "redirect:/advertisement/{id}/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        log.debug(exception.getMessage());

        return modelAndView;
    }
/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){

        log.error("Handling number format exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);
        log.debug(exception.getMessage());

        return modelAndView;
    }
    */
}
