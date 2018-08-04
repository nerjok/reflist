package com.refs.controllers;


import com.refs.commands.AdvertisementCommand;
import com.refs.commands.CategoryCommand;
import com.refs.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CategoryController {

    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping({"category", "categories/" })
    public String getCategoriesList(Model model) {

        model.addAttribute("categories", categoryService.getCategories());

        return "category/index";
    }

    @RequestMapping({"category/{id}/show"})
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("category", categoryService.findById(new Long(id)));

        return "category/show";
    }

    @RequestMapping({"category/new"})
    public String newCategory(Model model) {

        model.addAttribute("category", new CategoryCommand());

    return "category/categoryForm";
    }
    @GetMapping("category/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("category", categoryService.findCommandById(Long.valueOf(id)));
        return "category/categoryForm";
    }

    @PostMapping({"category", "category/"})
    public String saveOrUpdate(@ModelAttribute CategoryCommand command) {
        CategoryCommand savedCommand = categoryService.saveCategoryCommand(command);

        return "redirect:/category/" + savedCommand.getId() + "/show";
    }

    @GetMapping("category/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        categoryService.deleteById(Long.valueOf(id));
        return "redirect:/category";
    }
}