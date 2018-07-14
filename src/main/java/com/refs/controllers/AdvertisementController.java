package com.refs.controllers;


import com.refs.commands.AdvertisementCommand;
import com.refs.services.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
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

        return "advertisements/show";
    }

    @GetMapping("advertisement/new")
    public String newRecipe(Model model){
        model.addAttribute("advertisement", new AdvertisementCommand());

        return "advertisements/advertisementform";
    }

    @GetMapping("advertisement/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("advertisement", advertisementService.findCommandById(Long.valueOf(id)));
        return "advertisements/advertisementform";
    }

    @PostMapping({"advertisement", "advertisement/"})
    public String saveOrUpdate(@ModelAttribute AdvertisementCommand command){
        AdvertisementCommand savedCommand = advertisementService.saveAdvertisementCommand(command);

        return "redirect:/advertisement/" + savedCommand.getId() + "/show";
    }

    @GetMapping("advertisement/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        advertisementService.deleteById(Long.valueOf(id));
        return "redirect:/advertisement";
    }
}
