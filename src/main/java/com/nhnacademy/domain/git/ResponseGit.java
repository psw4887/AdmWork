package com.nhnacademy.domain.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseGit {
    String clientId;

    String clientSecret;

    String code;

    String redirectUri;
}
