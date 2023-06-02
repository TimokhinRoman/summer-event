package ru.tmhn.summerevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tmhn.summerevent.model.RoleAdmin;

@Controller
public class PageController {

    @RoleAdmin
    @GetMapping("/admin")
    public String adminPage() {
        return "admin.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register.html";
    }
}
