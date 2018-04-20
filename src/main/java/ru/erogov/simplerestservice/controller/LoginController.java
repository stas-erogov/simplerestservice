package ru.erogov.simplerestservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.erogov.simplerestservice.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/public")
class LoginController {

    private final UserAuthenticationService userAuthenticationService;

    public LoginController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    String login (
            final HttpServletRequest request,
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return userAuthenticationService.login(username, password)
                .orElseThrow(()->new RuntimeException("Invalid login and/or password"));
    }
}
