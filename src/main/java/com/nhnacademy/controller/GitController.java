package com.nhnacademy.controller;

import com.nhnacademy.domain.git.ResponseGit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitController {

    @PostMapping("https://github.com/login/oauth/access_token")
    public void responseGitId(@RequestBody ResponseGit responseGit) {

    }
}
