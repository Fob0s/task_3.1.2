package ru.toropkin.crud.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.toropkin.crud.services.UsersService;
import ru.toropkin.crud.Entity.User;

@Controller
@RequestMapping("/users")
public class UserController {


    private UsersService usersService;
    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping()
    public String shoAll(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users/allUsers";
    }

    @GetMapping("/")
    public String getUserById(@RequestParam(name = "id", required = false) Integer id, Model model) {
        model.addAttribute("user", usersService.findOne(id));
        return "users/user";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "users/createUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/(id)/edit")
    public String updateUser(@RequestParam(name = "id") Integer id, Model model ) {
        model.addAttribute("updateUser", usersService.findOne(id));
        return "users/updateUser";
    }

    @PostMapping("/{id}")
    public String updating(@ModelAttribute("updateUser") User user) {
        usersService.update(user.getId(), user);
        return "redirect:/users";
    }

    @PostMapping("/(id)/delete")
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}
