package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @Valid @ModelAttribute User user,
                                     String verify, Errors errors) {

        if(errors.hasErrors()) {
            return "user/add";
        }

        if (user.getPassword().equals(verify)) {
            String welcome = "Welcome, " + user.getUsername() + "!";
            UserData.add(user);
            model.addAttribute("welcome", welcome);
            model.addAttribute("allUsers", UserData.getAll());
            return "user/index";
        } else {
            model.addAttribute("error", "Password Must Match!");
            return "user/add";
        }
    }
    @GetMapping("/detail")
    public String displayUserDetails(@RequestParam Integer userId, Model model) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }
}
