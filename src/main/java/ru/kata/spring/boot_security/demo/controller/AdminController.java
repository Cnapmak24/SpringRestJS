package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("")
//    public String showIndexPage(Model model) {
////        model.addAttribute("allUsers", userService.getAllUsers());
////        return "all-users-t";
//        return "index";
//    }

    @GetMapping()
    public String showAllUsers(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }


    @GetMapping("/registration")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "redirect:/admin";
    }


    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.findAll());
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User userUpdated, Model model) {
        userService.saveUser(userUpdated);
        return "redirect:/admin";
    }

    @RequestMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}