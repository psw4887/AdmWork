package com.nhnacademy.controller;

import com.nhnacademy.domain.git.ResponseGit;
import com.nhnacademy.service.CustomGitLoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitRestController {

    private final CustomGitLoginService service;

    public GitRestController(CustomGitLoginService service) {
        this.service = service;
    }

//    @GetMapping
//    public void buildGitAuthorized() {
//        service.
//    }

    @PostMapping("https://github.com/login/oauth/access_token")
    public void responseGitId(@RequestBody ResponseGit responseGit) {

    }
}
