package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/" )
    public String userList(Model model) {
        model.addAttribute("users", userServiceImpl.getUsers());
        return "userList";
    }

    @GetMapping("/userForm")
    public String userForm(@RequestParam(value="id", required = false) Integer id, Model model) {
        User user;
        if (id == null) {
            user = new User();
        } else {
            user = userServiceImpl.getUserById(id);
        }
        model.addAttribute("user", user);
        return "userForm";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userServiceImpl.saveUser(user);
        } else {
            userServiceImpl.updateUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/";
    }
}
