package ru.tmhn.summerevent.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.dto.UserDto;
import ru.tmhn.summerevent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }
}
