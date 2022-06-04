package com.nhnacademy.controller;

import com.nhnacademy.service.CustomGitLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GitController {
    private final CustomGitLoginService service;

    public GitController(CustomGitLoginService service) {
        this.service = service;
    }

    @GetMapping("/oauth2/authorization/github")
    public void readyGitLogin() {
        service.buildGitRequest();
    }
}
