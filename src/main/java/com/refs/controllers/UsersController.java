package com.refs.controllers;


import com.refs.commands.CategoryCommand;
import com.refs.commands.UserCommand;
import com.refs.services.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {

        model.addAttribute("users", userService.getUsers());
        return "users/index";
    }

    @GetMapping("/users/{id}/show")
    public String showUser(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findById(new Long(id)));

        return "users/show";
    }

    @GetMapping("users/new")
    public String newUser(Model model) {

        model.addAttribute("user", new UserCommand());
        return "users/userForm";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findById(new Long(id)));
        return "users/userForm";
    }

    @PostMapping({"users", "users/"})
    public String saveOrUpdate(@ModelAttribute UserCommand command) {
        UserCommand savedCommand = userService.saveUserCommand(command);

        return "redirect:/users/" + savedCommand.getId() + "/show";
    }

    @GetMapping("users/{id}/delete")
    public String deleteByID(@PathVariable String id, Model model) {

        userService.deleteById(new Long(id));
        return "redirect:/users";
    }

    @PostMapping("/users/register")
    public String registerUser(@ModelAttribute UserCommand command) {
        UserCommand savedCommand = userService.saveUserCommand(command);
        return "redirect:/users";
    }
}
