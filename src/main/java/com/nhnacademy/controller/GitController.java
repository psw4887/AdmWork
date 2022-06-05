package com.nhnacademy.controller;

import com.nhnacademy.domain.git.StateCookie;
import com.nhnacademy.service.CustomGitLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GitController {
    private final CustomGitLoginService service;

    public GitController(CustomGitLoginService service) {
        this.service = service;
    }

    @GetMapping("/oauth2/authorization/github")
    public void readyGitLogin(HttpServletResponse response) {
        try {
            StateCookie stateCookie = service.buildGitRequest();
            response.sendRedirect(stateCookie.getUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
