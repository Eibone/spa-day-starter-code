package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            String welcome = "Welcome, " + user.getUsername() + "!";
            model.addAttribute("username", user.getUsername());
            UserData.add(user);
            model.addAttribute("welcome", welcome);
            model.addAttribute("allUsers", UserData.getAll());
            return "user/index";
        } else {
            model.addAttribute("error", "Password Must Match!");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "user/add";
        }
    }
    @GetMapping("/detail")
    public String displayUserDetails(@RequestParam Integer userId, Model model) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }
}
