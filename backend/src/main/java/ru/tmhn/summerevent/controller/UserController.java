package ru.tmhn.summerevent.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tmhn.summerevent.dto.UserDto;
import ru.tmhn.summerevent.exception.PasswordDoesNotMatchException;
import ru.tmhn.summerevent.exception.UserAlreadyExistException;
import ru.tmhn.summerevent.service.UserService;

import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto dto, HttpServletRequest request) {
        try {
            UserDto registered = userService.register(dto);
            request.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(registered);
        } catch (PasswordDoesNotMatchException e) {
            return getErrorResponse("пароли не совпадают", HttpStatus.BAD_REQUEST);
        } catch (UserAlreadyExistException e) {
            return getErrorResponse("пользователь с таким email уже существует", HttpStatus.FORBIDDEN);
        } catch (ServletException e) {
            log.error("Failed to login", e);
            return getErrorResponse("что-то пошло не так", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Map<String, String>> getErrorResponse(String error, HttpStatusCode statusCode) {
        return new ResponseEntity<>(Collections.singletonMap("error", error), statusCode);
    }
}
