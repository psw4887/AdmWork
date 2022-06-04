package com.nhnacademy.service;

import com.nhnacademy.domain.git.RequestGit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpClient;

@Service("customGitLoginService")
public class CustomGitLoginService {

    public void buildGitRequest() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(10000);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(header);


        RestTemplate restTemplate = new RestTemplate(factory);

        UriComponents url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize")
                .queryParam("client_id", "fcaf07655762ce4a267b")
                .queryParam("redirect_uri", "http://localhost:8090/login/oauth2/code/github")
                .queryParam("scope", "name")
                .build();

        restTemplate.exchange(url.toString(), HttpMethod.GET, entity, RequestGit.class);
    }
}
