package com.nhnacademy.controller;

import com.nhnacademy.domain.git.AuthToken;
import com.nhnacademy.domain.git.CodeGit;
import com.nhnacademy.domain.git.GitProfile;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.service.CustomGitLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
                                 HttpServletResponse response,
                                 Model model) throws IOException {
        CodeGit codeGit = new CodeGit(request.getParameter("code"), request.getParameter("state"));
        if (!codeGit.getState().equals(service.getCookie().get("state"))) {
                response.sendRedirect("/auth/login");
        }

        AuthToken authToken = service.getAuthToken(codeGit);
        GitProfile gitProfile = service.getGithubProfile(authToken);
        Resident resident = service.findResidentByEmail(gitProfile, response);
        service.doGitLogin(resident);
        //FIXME : profile의 email이 계속 NULL??

        response.sendRedirect("/");
    }
}