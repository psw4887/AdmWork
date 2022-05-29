package com.nhnacademy.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResidentRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String genderCode;

    @NotBlank
    private LocalDateTime birth;

    @NotBlank
    private String birthPlace;

    @NotBlank
    private String baseAddress;
}
