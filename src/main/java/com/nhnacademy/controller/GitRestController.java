package com.nhnacademy.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.domain.git.AuthToken;
import com.nhnacademy.domain.git.CodeGit;
import com.nhnacademy.domain.git.GitProfile;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.service.CustomGitLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class GitRestController {

    private final CustomGitLoginService service;

    public GitRestController(CustomGitLoginService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login/oauth2/code/github")
    public void sendGitLoginInfo(HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        CodeGit codeGit = new CodeGit(request.getParameter("code"), request.getParameter("state"));
        if (!codeGit.getState().equals(service.getCookie().get("state"))) {
                response.sendRedirect("/auth/login");
        }

        AuthToken authToken = service.getAuthToken(codeGit);
        GitProfile gitProfile = service.getGithubProfile(authToken);
        Resident resident = service.findResidentByEmail(gitProfile.getEmail(), response);

        request.getSession().setAttribute("gitUser", resident);

        response.sendRedirect("/");
    }
}