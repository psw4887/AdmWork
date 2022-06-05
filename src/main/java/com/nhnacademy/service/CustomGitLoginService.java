package com.nhnacademy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.domain.git.AuthToken;
import com.nhnacademy.domain.git.CodeGit;
import com.nhnacademy.domain.git.GitProfile;
import com.nhnacademy.domain.git.StateCookie;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.ResidentRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service("customGitLoginService")
public class CustomGitLoginService {

    private final ResidentRepository rRepository;
    private final ConcurrentHashMap<String, String> cookie = new ConcurrentHashMap<>();

    public CustomGitLoginService(ResidentRepository rRepository) {
        this.rRepository = rRepository;
    }

    public ConcurrentHashMap<String, String> getCookie() {
        return cookie;
    }

    public Resident findResidentByEmail(String email, HttpServletResponse response) throws IOException {
        if (rRepository.findByUserEmail(email).isEmpty()) {
            response.sendRedirect("auth/login");
        }
        return rRepository.findByUserEmail(email).orElseThrow(ResidentNotFoundException::new);
    }

    public StateCookie buildGitRequest() {
        String state = UUID.randomUUID().toString();

        UriComponents url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize")
                .queryParam("client_id", "fcaf07655762ce4a267b")
                .queryParam("redirect_uri", "http://localhost:8090/login/oauth2/code/github")
                .queryParam("scope", "name")
                .queryParam("state", state)
                .build();
        cookie.put("state", state);

        return new StateCookie(state, url.toString());
    }

    public AuthToken getAuthToken(CodeGit codeGit) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String, String>> codeRequestHttpEntity = getCodeRequestHttpEntity(codeGit.getCode());
        RestTemplate tokenRequestTemplate = new RestTemplate();

        ResponseEntity<String> response = tokenRequestTemplate.exchange(
                "https://github.com/login/oauth/access_token",
                HttpMethod.POST,
                codeRequestHttpEntity,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), AuthToken.class);
    }

    public GitProfile getGithubProfile(AuthToken authToken) throws JsonProcessingException {
        RestTemplate requestTemplate = new RestTemplate();
        ResponseEntity<String> profileResponse = requestTemplate.exchange(
             "https://api.github.com/user",
             HttpMethod.GET,
             getProfileRequestEntity(authToken),
             String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(profileResponse.getBody(), GitProfile.class);
    }

    private HttpEntity<MultiValueMap<String, String>> getCodeRequestHttpEntity(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "fcaf07655762ce4a267b");
        params.add("client_secret", "22e83265d9668b2f67f4f0570f57ca2877dc9509");
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> getProfileRequestEntity(AuthToken authToken) {

        HttpHeaders infoRequestHeaders = new HttpHeaders();
        infoRequestHeaders.add("Authorization", "token" + authToken.getAccessToken());
        return new HttpEntity<>(infoRequestHeaders);
    }
}
